package com.nttdata.alpaca.tech_test_spring.config.mq.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.config.mq.IMessageQueueConsumer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaConsumerImpl implements IMessageQueueConsumer {

	@Override
	@KafkaListener(topics = "${app.kafka.topic.cliente-topic}", groupId = "my-group")
	public void listenMessage(String message) {
		// TODO Auto-generated method stub
		
	}

}
