package com.manhcode.jms.p2p;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class EligibilityCheckListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		ObjectMessage obrcv = (ObjectMessage) message;
		Patienttttt patient = null;
		try {
			patient = (Patienttttt) obrcv.getObject();
			System.out.println(patient);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InitialContext context = null;
		Queue replyqueue = null;
		try {
			context = new InitialContext();
			replyqueue = (Queue) context.lookup("queue/replyQueue");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			MapMessage mapreply = jmsContext.createMapMessage();
			
			if (patient.getInsuaranceProvider().equals("Samsung")) {
				mapreply.setBoolean("eligible", true);
			} else {
				mapreply.setBoolean("eligible", false);
			}
			
			JMSProducer producer = jmsContext.createProducer();
			producer.send(replyqueue, mapreply);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

