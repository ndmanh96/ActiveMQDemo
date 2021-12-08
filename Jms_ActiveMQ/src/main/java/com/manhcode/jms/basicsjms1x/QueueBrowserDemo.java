package com.manhcode.jms.basicsjms1x;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueBrowserDemo {
	public static void main(String[] args) {
		InitialContext initialContext = null;
		Connection connection = null;
		try {
			initialContext = new InitialContext();

			// create connection look up from jndi.properties
			ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");

			connection = connectionFactory.createConnection();

			// create session
			Session session = connection.createSession();

			Queue queue = (Queue) initialContext.lookup("queue/myQueue");

			MessageProducer producer = session.createProducer(queue);

			TextMessage message1 = session.createTextMessage("I am the creator of my destiny 111");
			TextMessage message2 = session.createTextMessage("I am the creator of my destiny 222");

			producer.send(message1);
			System.out.println("Message Sent: " + message1.getText());

			producer.send(message2);
			System.out.println("Message Sent: " + message2.getText());

			// create queue browser

			QueueBrowser browser = session.createBrowser(queue);
			
			Enumeration messagesEnum = browser.getEnumeration();
			
			while(messagesEnum.hasMoreElements()) {
				TextMessage ms  = (TextMessage) messagesEnum.nextElement();
				System.out.println("Browsing: "+ms.getText());
			}

			// consumer
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			TextMessage messageReceive = (TextMessage) consumer.receive(5000);
			System.out.println("Message Receive: " + messageReceive.getText());
			
			TextMessage messageReceive2 = (TextMessage) consumer.receive(5000);
			System.out.println("Message Receive: " + messageReceive2.getText());

		} catch (NamingException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (initialContext != null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
