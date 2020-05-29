package com.rocketmq.main;

import com.rocketmq.service.MQProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageController.class);


    MQProducerService mqService = new MQProducerService();

    @Value("${rocketmq.topic}")
    private String MQTopic = "";
    @Value("${rocketmq.tag}")
    private String MQTag = "";

    @RequestMapping(path = {"/test/rocketmq"})
    public String SendMsg (){



        boolean rs = mqService.sendMsg("111");
        if(!rs){
            logger.error("mq send failed ");
            return "FAILURE";
        }
        return "SUCCESS";
    }
}
