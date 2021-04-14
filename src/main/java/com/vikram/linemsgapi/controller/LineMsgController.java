package com.vikram.linemsgapi.controller;

import static java.util.Collections.singletonList;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;

@LineMessageHandler
public class LineMsgController {

	private final Logger log = LoggerFactory.getLogger(LineMsgController.class);

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private LineBlobClient lineBlobClient;

	private static void sendMessageToClient() {
		final LineMessagingClient client = LineMessagingClient
				.builder(
						"6gQtTrQIysvNg1C/39SY9Jv8wJZtmbTE2XiKSlFAww4QtFb0ZpTh99TgeT3dmFSaTxpY2ie7JxhD9GKKUxt95vY2TrGfIH+NclfCIA09Uh6TJoOHv2WkLeGLpByN8stwRUofkCZx75Wyzqt0mwU8EwdB04t89/1O/w1cDnyilFU=")
				.build();

		final TextMessage textMessage = new TextMessage("hello");
		final PushMessage pushMessage = new PushMessage("vikram9886", textMessage);

		final BotApiResponse botApiResponse;
		try {
			botApiResponse = client.pushMessage(pushMessage).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(botApiResponse);

	}

	private void reply(@NonNull String replyToken, @NonNull List<Message> messages, boolean notificationDisabled) {
		try {
			BotApiResponse apiResponse = lineMessagingClient
					.replyMessage(new ReplyMessage(replyToken, messages, notificationDisabled)).get();
			log.info("Sent messages: {}", apiResponse);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	private void handleTextContent(String replyToken, Event event, TextMessageContent content) throws Exception {
		final String text = content.getText();

		log.info("Got text message from replyToken:{}: text:{} emojis:{}", replyToken, text, content.getEmojis());
		switch (text) {

		default:
			log.info("Returns echo message {}: {}", replyToken, text);
			this.reply(replyToken, singletonList(new TextMessage(text)), false);
			break;

		}
	}
	// ---------------------------------------------------------------------------------------------

	@EventMapping
	public void handleFollowEvent(FollowEvent event) {
		String replyToken = event.getReplyToken();
		this.reply(replyToken, singletonList(new TextMessage("Got followed event")), false);
	}

	@EventMapping
	public void handleJoinEvent(JoinEvent event) {
		String replyToken = event.getReplyToken();
		this.reply(replyToken, singletonList(new TextMessage("Joined " + event.getSource())), false);
	}

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		log.info("event: " + event);
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
		//final String originalMessageText = event.getMessage().getText();
		// return new TextMessage(originalMessageText);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}

}
