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

public class MessageDelay {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			producer.setDeliveryDelay(2000);
			TextMessage requestMessage = jmsContext.createTextMessage("MMMMMMM");
			producer.send(requestqueue, requestMessage);

		
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			TextMessage messageRev = (TextMessage) consumer.receive(5000);
			System.out.println(messageRev.getText());
			
				
		}
	}
}
