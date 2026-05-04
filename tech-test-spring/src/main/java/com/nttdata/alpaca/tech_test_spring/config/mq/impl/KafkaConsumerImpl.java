package com.nttdata.alpaca.tech_test_spring.config.mq.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.alpaca.tech_test_spring.application.mapper.ClienteMapper;
import com.nttdata.alpaca.tech_test_spring.application.mapper.ReportCustomerMovementMapper;
import com.nttdata.alpaca.tech_test_spring.application.services.ReportCustomerMovementService;
import com.nttdata.alpaca.tech_test_spring.config.mq.IMessageQueueConsumer;
import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetClienteByNombre;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.SaveReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.MovementEventDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerImpl implements IMessageQueueConsumer {

    private final ObjectMapper objectMapper;
	private final ReportCustomerMovementService reportCustomerMovementService;	

	@Override
	@KafkaListener(topics = "${app.kafka.topic.cliente-topic}", groupId = "my-group")
	public void listenMessage(String message) {
		try {
			log.info("Received Kafka message: {}", message);
			MovementEventDTO event = objectMapper.readValue(message, MovementEventDTO.class);
			ReportCustomerMovement movement = ReportCustomerMovementMapper.fromAccountEventToEntity(event);
			reportCustomerMovementService.save(movement)
					.subscribe(saved -> log.info("Saved movement for account: {}", saved.getNumeroCuenta()),
							 error -> log.error("Error saving movement: ", error));
		} catch (Exception e) {
			log.error("Error processing Kafka message: ", e);
		}
	}

}
