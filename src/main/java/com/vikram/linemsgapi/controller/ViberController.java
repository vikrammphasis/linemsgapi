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
		
		return "Webhook working";
	}
	
}
