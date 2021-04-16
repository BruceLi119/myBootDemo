//package com.asiaInfo.demo.pulsar;
//
//import com.asiaInfo.demo.controller.DistinctionLogController;
//import com.asiaInfo.demo.service.DistinctionLogService;
//import com.asiaInfo.demo.util.SpringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.pulsar.client.api.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.concurrent.TimeUnit;
//
///**
// * @description: 初始化pulsar
// * @author: li.shuGuang
// * @dateTime: 2021/4/14
// */
//@Component
//public class PulsarInit {
//
//    private static final Logger log = LoggerFactory.getLogger(DistinctionLogController.class);
//
//    @Value("${pulsar.url}")
//    private String url;
//    @Value("${pulsar.topic}")
//    private String topic;
//    @Value("${pulsar.subscription}")
//    private String subscription;
//
//
//    private PulsarClient client = null;
//    private Consumer consumer = null;
//
//    @Autowired
//    DistinctionLogService distinctionLogService;
//
//    /**
//     * 使用@PostConstruct注解用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化
//     */
//    @PostConstruct
//    public void initPulsar() throws Exception {
//        try {
//            //构造Pulsar client
//            client = PulsarClient.builder()
//                    .serviceUrl(url)
//                    .build();
//
//            //创建consumer
//            consumer = client.newConsumer()
//                    .topic(topic.split(","))
//                    .subscriptionName(subscription)
//                    .subscriptionType(SubscriptionType.Shared)//指定消费模式，包含：Exclusive，Failover，Shared，Key_Shared。默认Exclusive模式
//                    .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)//指定从哪里开始消费还有Latest，valueof可选，默认Latest
//                    .negativeAckRedeliveryDelay(60, TimeUnit.SECONDS)//指定消费失败后延迟多久broker重新发送消息给consumer，默认60s
//                    .subscribe();
//
//            // 开始消费
//            new Thread(() -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                PulsarInit pulsarInit = SpringUtils.getBean(PulsarInit.class);
//                try {
//                    pulsarInit.start();
//                } catch (Exception e) {
//                    log.error("消费Pulsar数据异常，停止Pulsar连接：", e);
//                    pulsarInit.close();
//                }
//            }).start();
//        } catch (Exception e) {
//            log.error("Pulsar初始化异常：", e);
//            throw e;
//        }
//    }
//
//    private void start() throws Exception {
//        //消费消息
//        while (true) {
//            Message message = consumer.receive();
//            String[] keyArr = message.getKey().split("_");
//            String jsons = new String(message.getData());
//
//            if (StringUtils.isNotEmpty(jsons)) {
//                try {
//                    log.info(jsons);
//                    //distinctionLogService.distinctionLogComplete(jsons);
//                } catch (Exception e) {
//                    log.error("消费Pulsar数据异常，key【{}】，json【{}】：", message.getKey(), jsons, e);
//                }
//            }
//            consumer.acknowledge(message);
//        }
//    }
//
//
//    public void close() {
//        try {
//            consumer.close();
//        } catch (PulsarClientException e) {
//            log.error("关闭Pulsar消费者失败：", e);
//        }
//        try {
//            client.close();
//        } catch (PulsarClientException e) {
//            log.error("关闭Pulsar连接失败：", e);
//        }
//    }
//
//}
