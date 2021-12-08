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

public class RequestReplyJMSheader {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		Queue replyqueue = (Queue) context.lookup("queue/replyQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			TextMessage requestMessage = jmsContext.createTextMessage("Request Message");
			requestMessage.setJMSReplyTo(replyqueue);
			producer.send(requestqueue, requestMessage);
		
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			TextMessage messageRev = (TextMessage) consumer.receive();
			System.out.println(messageRev.getText());
			
			JMSProducer replyProducer = jmsContext.createProducer();
			replyProducer.send(messageRev.getJMSReplyTo(), "Reply Message");
			
			JMSConsumer replyConsumer = jmsContext.createConsumer(replyqueue);
			String messagerep = replyConsumer.receiveBody(String.class);
			System.out.println(messagerep);

			
		}
	}
}
