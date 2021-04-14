package com.vikram.linemsgapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LinemsgapiApplication {
	
	
	private final Logger log = LoggerFactory.getLogger(LinemsgapiApplication.class);

//	@Value( "${line.bot.channel-token}" )
//	static private String token;

	public static void main(String[] args) {
		SpringApplication.run(LinemsgapiApplication.class, args);
		
	}

	
}
