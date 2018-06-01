package com.fengyj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;

public class MsgReturnCallback implements ReturnCallback {
	
	private Logger logger = LoggerFactory.getLogger(MsgReturnCallback.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
 
		logger.error("发送信息失败,信息重发::::"+message.getBody());
		//重新发布
        RepublishMessageRecoverer recoverer = new RepublishMessageRecoverer(rabbitTemplate,"errorExchange", "errorRoutingKey");
        Throwable cause = new Exception(new Exception("route_fail_and_republish"));
        recoverer.recover(message,cause);
	}

}
