package com.manhcode.jms.messgroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.manhcode.jms.security.Patienttttt;

public class MessageGroupingDemo {
	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		Map<String, String> receivedMessages = new ConcurrentHashMap<String, String>();
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();
				JMSContext jmsContext2 = cf.createContext();) {


			JMSProducer producer = jmsContext.createProducer();
			
			//
			JMSConsumer consumer1 = jmsContext2.createConsumer(requestqueue);
			JMSConsumer consumer2 = jmsContext2.createConsumer(requestqueue);
			consumer1.setMessageListener(new MyListener("Consumer-1", receivedMessages));
			consumer2.setMessageListener(new MyListener("Consumer-2", receivedMessages));
			
			
			int count = 10;
			TextMessage[] messages = new TextMessage[count];
			for (int i = 0; i < count; i++) {
				messages[i] = jmsContext.createTextMessage("Group-0 message"+i);
				messages[i].setStringProperty("JMSXGroupID", "Group-0");
				producer.send(requestqueue, messages[i]);
			}
			
			Thread.sleep(2000);
			
			for (TextMessage ms : messages) {
				if (!receivedMessages.get(ms.getText()).equals("Consumer-1")) {
					throw new IllegalArgumentException("Group message "+ms.getText()+" has gone to the wrong receiver");
				}
			}
			
			
		}

	}
}

class MyListener implements MessageListener {
	private final String name;
	private final Map<String, String> receivedMessages;
	
	
	public MyListener(String name, Map<String, String> receivedMessages) {
		this.name = name;
		this.receivedMessages = receivedMessages;
	}


	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("Message Receive: "+textMessage.getText());
			System.out.println("Listener Name: "+name);
			receivedMessages.put(textMessage.getText(), name);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
