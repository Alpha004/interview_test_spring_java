package com.nttdata.alpaca.tech_test_spring.config.mq;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;

public interface MessageQueueProducer {
    public void sendClienteMessage(Cliente cliente);
}
