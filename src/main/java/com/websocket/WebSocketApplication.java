package com.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class WebSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketApplication.class, args);
	}
}
