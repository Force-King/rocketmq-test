package com.rocketmq.main;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.rocketmq.entity.StatLog;
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

        StatLog log = new StatLog().setActionId("10122").setAppId(5)
                .setChannel("android_tencent")
                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                .setDescription("{\"data\":[{\"index\":0,\"pid\":1000,\"targetType\":2,\"skuSeed\":null},{\"index\":1,\"pid\":1305,\"targetType\":2,\"skuSeed\":null}],\"channel\":\"baidu\",\"deviceId\":\"869868020358636\"}")
                .setId(1).setPageId(10051)
                .setPreAppId(0).setPrePageId(0).setUid(1111);

        boolean rs = mqService.sendMsg(log);
        if(!rs){
            logger.error("mq send failed log {}" + JSONObject.toJSONString(log));
            return "FAILURE";
        }
        return "SUCCESS";
    }
}
