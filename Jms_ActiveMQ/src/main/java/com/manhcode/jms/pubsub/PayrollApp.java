package com.manhcode.jms.pubsub;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.manhcode.jms.p2p.Patienttttt;

public class PayrollApp {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

		JMSConsumer consumer = jmsContext.createConsumer(topic);
		Message message = consumer.receive();
		Employee emprev = message.getBody(Employee.class);
		System.out.println("Payroll APP: "+emprev);
		}
	}
}
