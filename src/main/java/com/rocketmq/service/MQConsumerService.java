package com.rocketmq.service;

import com.alibaba.rocketmq.client.consumer.*;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class MQConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumerService.class);

    private static DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("mq_test_consumer");
    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<MessageQueue, Long>();

    private static final String mqUrl = "172.18.30.210:9876;172.18.30.211:9876;172.18.30.212:9876;172.18.31.94:9876;172.18.31.99:9876;172.18.31.117:9876";
    private static final String mqTopic = "mq_test_topic";
    private static final String mqTag = "mq_test_tag";


    public static void initConsumer(){
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("mq_test_consumer");
        consumer.setNamesrvAddr(mqUrl);
        try {
            consumer.start();
            System.out.println("MQ Consumer 启动成功！");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void consumeSchedule() throws MQClientException {
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("GroupName1");

        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        scheduleService.registerPullTaskCallback(mqTopic, new PullTaskCallback() {

            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                MQPullConsumer consumer = context.getPullConsumer();
                try {

                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if (offset < 0)
                        offset = 0;

                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    System.out.printf("%s%n", offset + "\t" + mq + "\t" + pullResult);
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            if(pullResult.getMsgFoundList().size()>0){
                                for(Message message: pullResult.getMsgFoundList()) {
                                    Thread.sleep(100);
                                    logger.info("consumer message:"+message.toString());
                                }
                            }

                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

                    context.setPullNextDelayTimeMillis(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scheduleService.start();
    }











}

