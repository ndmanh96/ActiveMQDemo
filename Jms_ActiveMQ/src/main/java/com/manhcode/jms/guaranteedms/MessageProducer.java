package com.manhcode.jms.guaranteedms;

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

//AUTO_ACKOWLEDGE
public class MessageProducer {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext(JMSContext.AUTO_ACKNOWLEDGE)) { //DUPS_OK_ACKNOWLEDGE, CLIENT_ACKNOWLEDGE, SESSION_TRANSACTED (context commit and rollback )

			JMSProducer producer = jmsContext.createProducer();
			producer.send(requestqueue, "Mesage 1");
			

		}

	}

}
