package com.shcepp.shdippsvr.business.util;

import com.shcepp.kafkaclient.consumer.KafkaClientConsumer;
import com.shcepp.kafkaclient.consumer.KafkaConsumerListener;
import com.shcepp.kafkaclient.producer.KafkaClientProducer;
import com.shcepp.kafkaclient.producer.KafkaProducerCallBack;
import com.shcepp.shdippsvr.business.cache.KafkaCache;
import com.shcepp.shdippsvr.business.config.BaseConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 和kafka的交互类
 *
 * @author BrunE
 * @date 2019-03-11 14:17
 **/
@Component
public class SendKafkaUtil {

    /**
     * 发送kafka
     *
     * @author maowc
     * 2018年5月2日
     */

    private Logger logger = LoggerFactory.getLogger(SendKafkaUtil.class);

    @Autowired
    private KafkaClientProducer<String, String> kafkaClientProducer;
    @Autowired
    private KafkaCache kafkaCache;

    /**
     * 推送kafka
     *
     * @return
     */
    public void sendKafka(String topic,String keyId,String valueJson)  throws Exception {

        //开关
        //String kafkaSwitch=DBConfig.getKafkaSwitch();
        String kafkaSwitch = "ON";

        logger.debug(String .format("[SendKafkaUtil] [sendKafka] message:{推送kafka,kafkaSwitch=%s}", kafkaSwitch));

        if (BaseConfig.SWITCH_OFF.equals(kafkaSwitch)) {
            logger.debug("推送kafka,kafka开关为OFF，不推送");
            return;
        }

        if (StringUtils.isBlank(topic)) {
            logger.debug("推送kafka,topic为空，不推送");
            return;
        }

        if (StringUtils.isBlank(keyId)) {
            logger.debug("推送kafka,keyId为空，不推送");
            return;
        }

        //keyId   ==CROSS_ORDER_ID或者DELIVERY_ID
        kafkaClientProducer.send(topic, keyId, valueJson, new KafkaProducerCallBack() {

            @Override
            public void success() {
                logger.debug(String
                                    .format("[SendKafkaUtil] [sendKafka] message:{推送kafka,topic=%s,keyId=%s,value=%s 发送成功}",
                                            topic,
                                            keyId,
                                            valueJson));
            }

            @Override
            public void failed() {
                logger.debug(String
                                    .format("[SendKafkaUtil] [sendKafka] message:{推送kafka,topic=%s,keyId=%s,value=%s 发送失败}",
                                            topic,
                                            keyId,
                                            valueJson));
            }
        });
    }

    /**
     * 获取topic
     *
     * @param template
     * @return
     */
    private String getTopic(String template) {
        String topic = "";
        switch (template) {
            case "Jg2BondInvtMergeError":
                topic = kafkaCache.getUpfBatchOrderTopic();//金关2 核注失败的异步通知格式
                break;

        }

        return topic;
    }

    /**
     * 接收kafka  一次取一条
     *
     * @param consumer
     * @param topicType
     * @param msgNum
     * @param sleepTime
     * @param logger
     */
    public String getMsgFromKafka(KafkaClientConsumer<String, String> consumer, String topicType, int msgNum, long sleepTime, Logger logger) {

        Map<String, String> msgMapValue = new HashMap<>();
        String topic = getTopic(topicType);

        if (StringUtils.isBlank(topic)) {
            return null;
        }

        consumer.setTopic(topic);

        List<KafkaConsumerListener<String, String>> listeners = new ArrayList<>();
        listeners.add((r) -> {

            // 从kafka中获取消息
            String value = r.value();

            if (StringUtils.isBlank(value)) {
                return;
            }

            msgMapValue.put("valueKafka", value);

        });

        consumer.setListeners(listeners);
        consumer.getInfos(msgNum, sleepTime);

        return msgMapValue.get("valueKafka");
    }
}

