package com.manhcode.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJmsApplicationTests {
	@Autowired
	MessageSender sender;
	
	@Test
	void testSendAndReceive() {
		sender.send("Hello Spring JMS!!!");
	}

}
