package com.rocketmq.service;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.rocketmq.entity.StatLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;


public class MQProducerService {

    private static final Logger logger = LoggerFactory.getLogger(MQProducerService.class);

    private static DefaultMQProducer producer = new DefaultMQProducer("producer");

    private static final String mqUrl = "172.18.30.210:9876;172.18.30.211:9876;172.18.30.212:9876;172.18.31.94:9876;172.18.31.99:9876;172.18.31.117:9876";
    //private static final String mqUrl = "192.168.180.12:9876";
    private static final String mqTopic = "mq_test_topic";
    private static final String mqTag = "mq_test_tag";

    private SendCallback mqcallback;


    public boolean sendMsg(StatLog log) {
        try {
            Message msg = new Message(mqTopic, mqTag, JSONObject.toJSONString(log).getBytes());

            this.producer.send(msg, mqcallback);
            return true;
        } catch (Exception e) {
            logger.error("mq send failed e {}" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void initProducer(){
        this.producer = new DefaultMQProducer("mq_test_producer");
        this.producer.setNamesrvAddr(mqUrl);
        try {
            producer.start();
            System.out.println("mq_test_producer 启动成功！mqUrl="+mqUrl);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        mqcallback = new MQCallbackImpl();
    }

    private class MQCallbackImpl implements SendCallback{

        @Override
        public void onSuccess(SendResult sendResult) {
            SendStatus status = sendResult.getSendStatus();
            if(status == SendStatus.SEND_OK){
                logger.info("mq send success, MsgId="+sendResult.getMsgId());
                System.out.println("mq send success, MsgId="+sendResult.getMsgId());
            }else{
                logger.error("mq send failed rs {}" + JSONObject.toJSONString(sendResult));
            }
        }

        @Override
        public void onException(Throwable e) {
            e.printStackTrace();
            logger.error("mq send failed e {}" + e.getMessage());
        }

    }


}

