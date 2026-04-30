package com.nttdata.alpaca.tech_test_spring.config.mq.impl;

import com.nttdata.alpaca.tech_test_spring.config.mq.IMessageQueueProducer;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@RequiredArgsConstructor
@Component
public class KafkaProducerImpl implements IMessageQueueProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendClienteMessage(Cliente cliente, 
    	@Value("${app.kafka.topic.account-topic}") String topic) {
        String json = new Gson().toJson(cliente);
        kafkaTemplate.send(topic,json);
    }
}
