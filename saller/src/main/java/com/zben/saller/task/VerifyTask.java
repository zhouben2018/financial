package com.zben.saller.task;

import com.zben.saller.enums.ChanEnum;
import com.zben.saller.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: zben
 * @Description:
 * @Date: 16:35 2019/6/18
 */
@Component
@EnableScheduling   //开启任务
public class VerifyTask {

    @Autowired
    private VerificationService verificationService;

//    @Scheduled(cron = "*/5 * * * * ? ") //每5秒执行
//    public void hello() {
//        System.out.println("hello");
//    }


    @Scheduled(cron = "0 0 1,3 * * ? ")
    public void makeVerificationFile() {
        Date yes = getYesDate();
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verificationService.makeVerificationFile(chanEnum.getChanId(), yes);
        }
    }

    @Scheduled(cron = "0 0 2,4 * * ? ")
    public void verifyOrders() {
        Date yes = getYesDate();
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verificationService.verifyOrders(chanEnum.getChanId(), yes);
        }
    }

    private Date getYesDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }
}
