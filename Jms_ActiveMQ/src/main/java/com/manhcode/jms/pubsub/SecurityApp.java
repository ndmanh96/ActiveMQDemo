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

public class SecurityApp {
	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
		//set client id, sub name
		jmsContext.setClientID("SecurityApp");
		JMSConsumer consumer = jmsContext.createDurableConsumer(topic, "subscription1");
		consumer.close();
		
		Thread.sleep(10000);
		
		consumer = jmsContext.createDurableConsumer(topic, "subscription1"); // backup again
		Message message = consumer.receive();
		Employee emprev = message.getBody(Employee.class);
		System.out.println("Security APP: "+emprev);
		
		consumer.close();
		jmsContext.unsubscribe("subscription1");
		}
	}
}
