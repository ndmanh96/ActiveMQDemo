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

public class MessageCustomeProperties {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			TextMessage requestMessage = jmsContext.createTextMessage("MMMMMMM");
			
			//custome properties
			requestMessage.setBooleanProperty("logged", true);
			requestMessage.setStringProperty("token", "123456");

			
			producer.send(requestqueue, requestMessage);

		
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			TextMessage messageRev = (TextMessage) consumer.receive();
			System.out.println(messageRev.getText());
			System.out.println(messageRev.getBooleanProperty("logged"));
			System.out.println(messageRev.getStringProperty("token"));
			
				
		}
	}
}
