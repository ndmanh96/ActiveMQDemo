package com.manhcode.jms.security;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class EligibilityChekerApp {
	public static void main(String[] args) throws NamingException, InterruptedException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestseQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext("eligibilityuser", "eligibilitypass")) { //define user name and password
			
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			consumer.setMessageListener(new EligibilityCheckListener());
			
			Thread.sleep(50000); // need live because listener
				
		}
	}
}

