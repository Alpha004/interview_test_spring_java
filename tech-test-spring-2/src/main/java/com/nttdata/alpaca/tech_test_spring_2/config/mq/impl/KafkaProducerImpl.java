package com.nttdata.alpaca.tech_test_spring_2.config.mq.impl;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import com.nttdata.alpaca.tech_test_spring_2.config.mq.IMessageQueueProducer;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementEventDTO;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaProducerImpl implements IMessageQueueProducer {
	
    private final KafkaTemplate<String, String> kafkaTemplate;    

	@Override
	public void sendMovement(MovementEventDTO movement, String topic) {		
		String json = new Gson().toJson(movement);
        kafkaTemplate.send(topic,json);	
        log.info("Movement sent to the Customer Service...");
	}
}
