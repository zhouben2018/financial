package com.zben.unit;

import com.zben.saller.backuprepository.OrderBackupRepository;
import com.zben.saller.repository.OrderRepository;
import com.zben.saller.service.VerificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderBackupRepository orderBackupRepository;

    @Autowired
    VerificationService verificationService;
    private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void text() throws ParseException {
        String date = "2019-06-16 17:20:20";
        Date day = DAY_FORMAT.parse(date);
        File file = verificationService.makeVerificationFile("111", day);
        System.out.println(file.getAbsolutePath());
    }
    @Test
    public void save() throws ParseException {
        String date = "2019-06-16 17:20:20";
        Date day = DAY_FORMAT.parse(date);
        verificationService.saveChanOrders("111", day);
    }

    @Test
    public void verifyOrders() throws ParseException {
        String date = "2019-06-16 17:20:20";
        Date day = DAY_FORMAT.parse(date);
        System.out.println(verificationService.verifyOrders("111", day));
    }

    @Test
    public void verifyBean() throws ParseException {
        System.out.println(orderRepository.findAll());
        System.out.println(orderBackupRepository.findAll());
    }
}
