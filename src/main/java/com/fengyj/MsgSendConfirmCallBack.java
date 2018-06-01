package com.fengyj;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSendConfirmCallBack implements ConfirmCallback {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(!ack) {
			
		}else {
			
		}
		
	}

	
}
