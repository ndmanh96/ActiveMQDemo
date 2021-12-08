package com.manhcode.jms.pubsub;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.manhcode.jms.p2p.Patienttttt;

public class HRApp {
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			Employee employee = new Employee(1, "Manh", "manh.nd4@samsung.com", "SamSung", "0327998425");

			JMSProducer producer = jmsContext.createProducer();
			for (int i = 1; i <= 10; i++) {
				producer.send(topic, employee);
				System.out.println("Message Sent");
			}
		}
	}
}
