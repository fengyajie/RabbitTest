package com.fengyj;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:application-mq.xml"})

public class TestQueue{

    private Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    MQProducer sender;
    
    @Autowired
    RabbitTemplate rabbit;

    @Test
    public void testSendString() throws Exception {
        sender.sendDataToQueue("xxxx", "ssssssssssss");
    }

    @Test
    public void send() throws Exception {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);

        Map<String, String> map = new HashMap<String, String>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("orderId", "20160131123");
        data.put("status", "SUCCESS");

        map.put("sign", "adfad-3145wef-wfed");
        map.put("data", gson.toJson(data));

        Message message = new Message(gson.toJson(map).getBytes(), messageProperties);
        rabbit.convertAndSend(message);
    }
}