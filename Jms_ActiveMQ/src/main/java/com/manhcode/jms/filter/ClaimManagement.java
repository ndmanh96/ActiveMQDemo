package com.manhcode.jms.filter;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.manhcode.jms.pubsub.Employee;

public class ClaimManagement {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/claimQueue");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			//
			JMSProducer producer = jmsContext.createProducer();
			ObjectMessage obms = jmsContext.createObjectMessage();
			obms.setObject(new Claim(1, "Manh", "Dentis", "Samsung", 1000d));
			
			//filter properties
			String filterproperties = "hostpitalId";
			obms.setIntProperty(filterproperties, 1);
			
			
			producer.send(queue, obms);
			
			//some another
			/*
			 * jmsContext.createConsumer(queue, "hostpitalId BETWEEN 1 AND 10");
			 * jmsContext.createConsumer(queue, "doctorName LIKE 'M%'"); -- tương tự like SQL
			 * jmsContext.createConsumer(queue, "doctorName IN ('Manh', 'Ninh')");
			 * jmsContext.createConsumer(queue, "doctorName IN ('Manh', 'Ninh') OR JMSPriority BETWEEN 3 AND 6"); -- filterheader
			 * */
			JMSConsumer consumer = jmsContext.createConsumer(queue, "hostpitalId=1");
			
			
			Claim cl = consumer.receiveBody(Claim.class);
			System.out.println(cl);
		
		}

	}

}
