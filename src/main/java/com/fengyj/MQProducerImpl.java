package com.fengyj;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class MQProducerImpl implements MQProducer {

	@Autowired
    RabbitTemplate rabbit;

    private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
    /* (non-Javadoc)
     * @see com.stnts.tita.rm.api.mq.MQProducer#sendDataToQueue(java.lang.String, java.lang.Object)
     */
    public void sendDataToQueue(String queueKey, Object object) {
        try {
        	rabbit.convertAndSend(queueKey, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	

        /*try {
            amqpTemplate.convertAndSend(queueKey, object);
        } catch (Exception e) {
            LOGGER.error(e);
        }*/
    	
    	/*//1.创建一个ConnectionFactory连接工厂connectionFactory  
        ConnectionFactory connectionFactory = new ConnectionFactory();  
        //2.通过connectionFactory设置RabbitMQ所在IP等信息  
        connectionFactory.setHost("127.0.0.1");  
         connectionFactory.setPort(5672); //指定端口  
        connectionFactory.setUsername("guest");//用户名  
        connectionFactory.setPassword("guest");//密码  
        //3.通过connectionFactory创建一个连接connection  
        Connection connection = null;
		try {
			connection = connectionFactory.newConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //4.通过connection创建一个频道channel  
        Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //5.通过channel指定一个队列  
        try {
			channel.queueDeclare("queue-test", false, false, false, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //发送的消息    
        String message = "hello world!";  
        //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）  
        try {
			channel.basicPublish("", "queue-test", null, message.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("向" + "queue-test" + "中添加了一条消息:" + message);  
        //7.关闭频道  
        try {
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        //8.关闭连接  
        try {
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
*/
    

    }

}
