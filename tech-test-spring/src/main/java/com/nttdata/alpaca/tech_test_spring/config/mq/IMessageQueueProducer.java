package com.nttdata.alpaca.tech_test_spring.config.mq;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;

public interface IMessageQueueProducer {
    public void sendClienteMessage(Cliente cliente, String topic);
}
