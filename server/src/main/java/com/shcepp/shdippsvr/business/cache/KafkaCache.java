package com.shcepp.shdippsvr.business.cache;
import com.shcepp.kafkaclient.KafkaConfig;
import com.shcepp.kafkaclient.producer.KafkaClientProducer;
import com.shcepp.shdippsvr.business.config.BaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 和kafka交互的各种杜建
 */
@Configuration
@ConfigurationProperties("sys")
public class KafkaCache implements BaseConfig {
	private int kafkaProducerPoolSize = 10;
	private KafkaConfig kafkaConfig;
	@Value("${KAFKA_SYN_SWITCH:ON}")//向商户发送订单申报结果的延迟时间
	private String SynSwitch;


	public KafkaConfig getKafkaConfig() {
		return kafkaConfig;
	}
	public void setKafkaConfig(KafkaConfig kafkaConfig) {
		this.kafkaConfig = kafkaConfig;
	}
	@Bean(name = "kafkaClientProducer")
	public KafkaClientProducer getKafkaProducerPool(){
		return new KafkaClientProducer(kafkaConfig);
	}


	@Value("${UPF_BATCH_ORDER_TOPIC:upfBatchOrder}")//订单申报的topic名称
	private   String upfBatchOrderTopic ;

	public String getUpfBatchOrderTopic() {
		return upfBatchOrderTopic;
	}

	public void setUpfBatchOrderTopic(String upfBatchOrderTopic) {
		this.upfBatchOrderTopic = upfBatchOrderTopic;
	}

	public String getSynSwitch() {
		return SynSwitch;
	}

	public void setSynSwitch(String synSwitch) {
		SynSwitch = synSwitch;
	}
}
