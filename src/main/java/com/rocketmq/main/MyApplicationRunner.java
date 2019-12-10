package com.rocketmq.main;

import com.rocketmq.service.MQConsumerService;
import com.rocketmq.service.MQProducerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments var1) throws Exception{
        //初始化Producer
        MQProducerService producerService = new MQProducerService();
        producerService.initProducer();
        //初始化Consumer
        MQConsumerService.initConsumer();
        //启动消费定时任务
        MQConsumerService.consumeSchedule();
    }
}
