package com.nttdata.alpaca.tech_test_spring.config.mq;

public interface IMessageQueueConsumer {
	public void listenMessage(String message);
}
