package com.manhcode.jms.jms2x;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class JMSContextDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/myQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			jmsContext.createProducer().send(queue, "Message from jmscontext");
			
			String messageRe = jmsContext.createConsumer(queue).receiveBody(String.class);
			
			System.out.println(messageRe);
		}
	}

}
