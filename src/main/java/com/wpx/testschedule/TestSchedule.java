package com.wpx.testschedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: wpx
 * @Date: 2020/3/9 11:32
 * @Version: V_1.0.0
 */
@Component
public class TestSchedule {

    @Scheduled(cron = "0 0 0 * * ?")  //完成任务，每天0点定时清理缓存
    public void printDate(){
        System.out.println(new Date().toString());
    }
}
