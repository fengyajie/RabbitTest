package com.fengyj;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EndPoint {

	
	protected Channel channel;  
    protected Connection connection;  
    protected String endPointName;  
       
    public EndPoint(String endpointName) throws IOException{  
         this.endPointName = endpointName;  
           
         //Create a connection factory  
         ConnectionFactory factory = new ConnectionFactory();  
           
         //hostname of your rabbitmq server  
         factory.setHost("192.168.66.66");  
         factory.setPort(15672);  
         factory.setUsername("guest");  
         factory.setPassword("guest");  
           
         //getting a connection  
         try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}  
           
         //creating a channel  
         channel = connection.createChannel();  
           
         //declaring a queue for this channel. If queue does not exist,  
         //it will be created on the server.  
         channel.queueDeclare(endpointName, false, false, false, null);  
    }  
       
       
    /** 
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。 
     * @throws IOException 
     */  
     public void close() throws IOException{  
         try {
			this.channel.close();
			this.connection.close();  
		} catch (TimeoutException e) {
			e.printStackTrace();
		}  
     }  



}
