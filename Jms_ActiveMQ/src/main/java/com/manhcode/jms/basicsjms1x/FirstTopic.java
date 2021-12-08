package com.manhcode.jms.basicsjms1x;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstTopic {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InitialContext initialContext = null;
		Connection connection = null;

		initialContext = new InitialContext();

		// create connection look up from jndi.properties
		ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

		connection = connectionFactory.createConnection();

		// create Topic
		Topic topic = (Topic) initialContext.lookup("topic/myTopic");

		// create session
		Session session = connection.createSession();
		
		//producer with topic
		MessageProducer producer = session.createProducer(topic);
		
		//cosumer
		MessageConsumer consumer = session.createConsumer(topic);
		MessageConsumer consumer2 = session.createConsumer(topic);
		
		//send
		TextMessage message = session.createTextMessage("I am the creator of my destiny");
		
		producer.send(message);
		
		
		//receive
		connection.start();
		
		TextMessage message1 = (TextMessage) consumer.receive(5000);
		System.out.println("Consumer 1: "+message1.getText());
		
		TextMessage message2 = (TextMessage) consumer2.receive(5000);
		System.out.println("Consumer 2: "+message2.getText());
		
		connection.close();
		initialContext.close();
		
		
		
		

	}

}
