package com.nttdata.alpaca.tech_test_spring.config.mq;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

@RequiredArgsConstructor
@Component
public class KafkaProducerImpl implements MessageQueueProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.cliente-topic}")
    private String topic;

    @Override
    public void sendClienteMessage(Cliente cliente) {
        String json = new Gson().toJson(cliente);
        kafkaTemplate.send(topic,json);
    }
}
