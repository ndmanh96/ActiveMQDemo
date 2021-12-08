package com.manhcode.jms.messagestructure;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class RequestReplyBasicDemo {
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		Queue replyqueue = (Queue) context.lookup("queue/replyQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			producer.send(requestqueue, "requestmessage");
		
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			String message = consumer.receiveBody(String.class);
			System.out.println(message);
			
			JMSProducer replyProducer = jmsContext.createProducer();
			replyProducer.send(replyqueue, "reply message");
			
			JMSConsumer replyConsumer = jmsContext.createConsumer(replyqueue);
			String messagerep = replyConsumer.receiveBody(String.class);
			System.out.println(messagerep);

			
		}
	}
}
