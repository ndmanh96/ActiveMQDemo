package com.manhcode.jms.p2p;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ClinicalApp {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		Queue replyqueue = (Queue) context.lookup("queue/replyQueue");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			JMSProducer producer = jmsContext.createProducer();

			// Patienttt
			ObjectMessage obms = jmsContext.createObjectMessage();
			obms.setObject(new Patienttttt(1, "Manh", "Samsung", 1.2d, 2.5d));
			producer.send(requestqueue, obms);

			// check reply
			JMSConsumer consumer = jmsContext.createConsumer(replyqueue);
			MapMessage maprev = (MapMessage) consumer.receive(30000);
			System.out.println("Elibility: "+maprev.getBoolean("eligible"));

		}

	}

}
