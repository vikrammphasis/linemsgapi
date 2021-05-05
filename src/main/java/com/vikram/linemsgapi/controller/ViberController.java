package com.vikram.linemsgapi.controller;

import java.net.URI;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.ConversationsApi;
import com.zendesk.sunshine_conversations_client.api.IntegrationsApi;
import com.zendesk.sunshine_conversations_client.api.MessagesApi;
import com.zendesk.sunshine_conversations_client.api.ParticipantsApi;
import com.zendesk.sunshine_conversations_client.api.UsersApi;
import com.zendesk.sunshine_conversations_client.api.WebhooksApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.Author;
import com.zendesk.sunshine_conversations_client.model.ConversationListFilter;
import com.zendesk.sunshine_conversations_client.model.ConversationListResponse;
import com.zendesk.sunshine_conversations_client.model.Integration;
import com.zendesk.sunshine_conversations_client.model.IntegrationListFilter;
import com.zendesk.sunshine_conversations_client.model.IntegrationListResponse;
import com.zendesk.sunshine_conversations_client.model.MessagePost;
import com.zendesk.sunshine_conversations_client.model.MessagePostResponse;
import com.zendesk.sunshine_conversations_client.model.Page;
import com.zendesk.sunshine_conversations_client.model.TextMessage;
import com.zendesk.sunshine_conversations_client.model.UserResponse;
import com.zendesk.sunshine_conversations_client.model.WebhookCreateBody;
import com.zendesk.sunshine_conversations_client.model.WebhookListResponse;
import com.zendesk.sunshine_conversations_client.model.WebhookResponse;

@RestController
public class ViberController {

	@RequestMapping(value = "/vibertest")
	public String getSmoochiapi() {
		
		
		return "";
	}

	
	@RequestMapping(value = "/viberwebhook", method = RequestMethod.POST)
	public String smoochMessagesPostResponse(@RequestBody String body) {
		
		System.out.println(body);
		
		String send_message_url = "https://chatapi.viber.com/pa/send_message";
		
		try {
			// JSONParser reads the data from string object and break each data
			// into key value pairs
			JSONParser parse = new JSONParser();
			// Type caste the parsed json data in json object
			JSONObject jobj = (JSONObject) parse.parse(body);
			
			
			JSONObject jsonobj_1 = (JSONObject) jobj.get("sender");
			String senderid = (String) jsonobj_1.get("id");
			
			JSONObject jsonobj_2 = (JSONObject) jobj.get("message");
			String text = (String) jsonobj_2.get("text");
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Viber-Auth-Token", "4d4ec1b9f867d0f2-ce22aff66510a7a8-77db4313bbf66fb6");

			JSONObject senderObject = new JSONObject();
			senderObject.put("name", "Vikram Sender");
			senderObject.put("avatar", "https://share.cdn.viber.com/pg_download?id=0-04-01-eeeae57ec8a4ab68edf20e7adb2bc89f85f01ee54a43c90e04247f17d44b509b&filetype=jpg&type=icon");

		

			JSONObject requestJsonObject = new JSONObject();
			requestJsonObject.put("receiver", senderid);
			requestJsonObject.put("min_api_version", 1);
			requestJsonObject.put("tracking_data", "tracking data");
			requestJsonObject.put("type", "text");
			requestJsonObject.put("sender", senderObject);
			requestJsonObject.put("text", "Thanks for your message to our Viber bot. <" + text + ">. This is standalone Java microservice response");

			HttpEntity<String> request = new HttpEntity<String>(requestJsonObject.toString(), headers);
			URI locationHeader = restTemplate.postForLocation(send_message_url, request);
			
			System.out.println("locationHeader in viber webhook api");
			System.out.println(locationHeader);

			
		}catch(Exception e) {
			
		}

		
		
		
		
		
		return "Webhook working";
	}
	
}
