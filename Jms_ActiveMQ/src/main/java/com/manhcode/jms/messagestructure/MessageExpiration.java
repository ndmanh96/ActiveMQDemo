package com.manhcode.jms.messagestructure;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageExpiration {
	public static void main(String[] args) throws NamingException, InterruptedException, JMSException {
		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/myQueue");
		Queue expiryqueue = (Queue) context.lookup("queue/expiryQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			producer.setTimeToLive(2000); // time of live message
			producer.send(queue, "MMMM");
			System.out.println("Send message: ");
			
			Thread.sleep(5000);
			
			
			JMSConsumer consumer = jmsContext.createConsumer(queue);
			TextMessage ms = (TextMessage) consumer.receive(5000);
			System.out.println(ms);
			
			
			//get expiry message
			System.out.println(jmsContext.createConsumer(expiryqueue).receiveBody(String.class));
			
			
			
		}
	}
}
