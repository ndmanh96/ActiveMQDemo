package com.manhcode.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsApplication.class, args);
	}

}
