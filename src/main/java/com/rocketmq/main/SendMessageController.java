package com.rocketmq.main;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.rocketmq.service.MQProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

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
            logger.error("mq send failed log {}" + JSONObject.toJSONString(log));
            return "FAILURE";
        }
        return "SUCCESS";
    }
}
