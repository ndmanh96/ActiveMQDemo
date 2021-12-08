package com.manhcode.jms.messagestructure;

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageType {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Queue requestqueue = (Queue) context.lookup("queue/requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); JMSContext jmsContext = cf.createContext()) {
			
			JMSProducer producer = jmsContext.createProducer();
			
			//BytesMessage
//			BytesMessage bytems = jmsContext.createBytesMessage();
//			bytems.writeUTF("Manh");
//			bytems.writeLong(123456);
//			producer.send(requestqueue, bytems);
			
			//Stream Message
//			StreamMessage msstream = jmsContext.createStreamMessage();
//			msstream.writeBoolean(true);
//			msstream.writeFloat(3.0f);
//			producer.send(requestqueue, msstream);
			
			//MapMessage
//			MapMessage mapms = jmsContext.createMapMessage();
//			mapms.setBoolean("isChecked", false);
//			producer.send(requestqueue, mapms);
			
			//ObjectMessage
			ObjectMessage obms = jmsContext.createObjectMessage();
			obms.setObject(new Patient(1, "Manh"));
			producer.send(requestqueue, obms);

		
			JMSConsumer consumer = jmsContext.createConsumer(requestqueue);
			//BytesMessage
//			BytesMessage msrcv = (BytesMessage) consumer.receive(5000);
//			System.out.println(msrcv.readUTF());
//			System.out.println(msrcv.readLong());
			
			//Stream Message
//			StreamMessage msrcv = (StreamMessage) consumer.receive(5000);
//			System.out.println(msrcv.readBoolean());
//			System.out.println(msrcv.readFloat());
			
			//Map Message
//			MapMessage maprcv = (MapMessage) consumer.receive(4000);
//			System.out.println(maprcv.getBoolean("ischecked"));

			
			//ObjectMessage
			ObjectMessage obrcv = (ObjectMessage) consumer.receive(5000);
			System.out.println(obrcv.getObject());
			
		}
	}
}

class Patient implements Serializable {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Patient(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + "]";
	}
	
	
}
