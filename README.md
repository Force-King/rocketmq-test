# RocketMQ压力测试程序

## 包含功能：

### 1. MQ 消息发送API

### 2. MQ 消息消费

#### topic :
mq_test_topic

使用时修改ip为自己的MQ集群namesrv地址即可

##

## 使用方式:

### 1. 启动

``
sh ./bin/start.sh
``

### 2. 使用AB 压测

压测命令：
``
ab -n 50000 -c 100 http://localhost:8080/test/rocketmq
``

