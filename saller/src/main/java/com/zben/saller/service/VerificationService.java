package com.zben.saller.service;

import com.zben.entity.VerificationOrder;
import com.zben.saller.enums.ChanEnum;
import com.zben.saller.repository.VerificationRepository;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zben
 * @Description:对账服务
 * @Date: 13:21 2019/6/16
 */
@Service
public class VerificationService {

    private static Logger LOG = LoggerFactory.getLogger(VerificationService.class);

    private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat DAY_FORMAT_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String END_LINE = System.getProperty("line.separator", "\n");

    @Value("${verification.rootdir:/Users/zhouben/verification}")
    private String rootDir;

    @Autowired
    private VerificationRepository verificationRepository;

    /**
     * 生成某个渠道某天的对账文件
     * @param chanId
     * @param day
     * @return
     */
    public File makeVerificationFile(String chanId, Date day) {
        File file = getPath(rootDir, chanId, day);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取起始日期
        Date start = getStartDate(day);
        Date end = new Date(start.getTime() + 1000 * 60 * 60 * 24);
        List<String> orders = verificationRepository.queryVerificationOrders(chanId, start, end);
        String content = String.join(END_LINE, orders);
        FileUtil.writeAsString(file, content);
        return file;
    }

    /**
     * 起始时间
     * @param day
     * @return
     */
    private Date getStartDate(Date day) {
        String startStr = DAY_FORMAT.format(day);
        Date start = null;
        try {
            start = DAY_FORMAT.parse(startStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 获取对账文件路径
     * @param chanId
     * @param day
     * @return
     */
    public File getPath(String rootDir, String chanId, Date day) {
        String name = DAY_FORMAT.format(day) + "-" + chanId + ".txt";
        File file = Paths.get(rootDir, name).toFile();
        return file;
    }

    /**
     * 保存渠道订单数据
     * @param chanId
     * @param day
     */
    public void saveChanOrders(String chanId, Date day) {
        ChanEnum chanEnum = ChanEnum.getByChanId(chanId);

        File file = getPath(chanEnum.getRootDir(), chanId, day);
        if (!file.exists()) {
            return;
        }
        String content = null;
        try {
            content = FileUtil.readAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = content.split(END_LINE);
        List<VerificationOrder> orders = new ArrayList<>();
        for (String line : split) {
            orders.add(parseLine(line));
        }
        verificationRepository.save(orders);
    }

    /**
     * 根据顺序解析字符串创建对账订单
     * @param line
     * @return
     */
    public static VerificationOrder parseLine(String line) {
        VerificationOrder order = new VerificationOrder();
        String[] split = line.split("\\|");
        //order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,DATE_FORMAT( create_at
        order.setOrderId(split[0]);
        order.setOuterOrderId(split[1]);
        order.setChanId(split[2]);
        order.setChanUserId(split[3]);
        order.setProductId(split[4]);
        order.setOrderType(split[5]);
        order.setAmount(new BigDecimal(split[6]));
        try {
            order.setCreateAt(DAY_FORMAT_SECOND.parse(split[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;

    }

    /**
     * 对账订单
     * @param chanId
     * @param day
     * @return
     */
    public List<String> verifyOrders(String chanId, Date day) {
        Date start = getStartDate(day); //起始时间
        Date end = new Date(start.getTime() + 1000 * 60 * 60 * 24); //结束时间
        List<String> errors = new ArrayList<>();
        List<String> excessOrders = verificationRepository.queryExcessOrders(chanId, start, end);
        List<String> missOrders = verificationRepository.queryMissOrders(chanId, start, end);
        List<String> differentOrders = verificationRepository.queryDifferentOrders(chanId, start, end);

        errors.add("长款订单号：" + String.join(",", excessOrders));
        errors.add("漏单订单号：" + String.join(",", missOrders));
        errors.add("不一致订单号：" + String.join(",", differentOrders));
        return errors;
    }
}
