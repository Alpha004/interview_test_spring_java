package com.nttdata.alpaca.tech_test_spring_2.config.mq;

import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementEventDTO;

public interface IMessageQueueProducer {
    public void sendMovement(MovementEventDTO movement, String topic);
}
