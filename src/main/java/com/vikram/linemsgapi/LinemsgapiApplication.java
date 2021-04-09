package com.vikram.linemsgapi;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class LinemsgapiApplication {
	
	
	private final Logger log = LoggerFactory.getLogger(LinemsgapiApplication.class);

//	@Value( "${line.bot.channel-token}" )
//	static private String token;

	public static void main(String[] args) {
		SpringApplication.run(LinemsgapiApplication.class, args);
		
		sendMessageToClient();
	}

	private static void sendMessageToClient() {
		final LineMessagingClient client = LineMessagingClient
		        .builder("6gQtTrQIysvNg1C/39SY9Jv8wJZtmbTE2XiKSlFAww4QtFb0ZpTh99TgeT3dmFSaTxpY2ie7JxhD9GKKUxt95vY2TrGfIH+NclfCIA09Uh6TJoOHv2WkLeGLpByN8stwRUofkCZx75Wyzqt0mwU8EwdB04t89/1O/w1cDnyilFU=")
		        .build();

		final TextMessage textMessage = new TextMessage("hello");
		final PushMessage pushMessage = new PushMessage(
		        "vikram9886",
		        textMessage);

		final BotApiResponse botApiResponse;
		try {
		    botApiResponse = client.pushMessage(pushMessage).get();
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return;
		}

		System.out.println(botApiResponse);

		
	}

	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		log.info("event: " + event);
		final String originalMessageText = event.getMessage().getText();
		return new TextMessage(originalMessageText);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}
