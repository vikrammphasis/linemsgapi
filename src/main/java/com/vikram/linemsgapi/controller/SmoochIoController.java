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
public class SmoochIoController {

	@RequestMapping(value = "/smoochiotest")
	public String getSmoochiapi() {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath("https://api.smooch.io");

		// Configure HTTP basic authorization: basicAuth
		HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
		basicAuth.setUsername("app_607faf3e7db9a300d3ee3ef9");
		basicAuth.setPassword("s72ROpgwUCjih2dyGr3_maCEqTkZ984mX7J4LGQNNRjQnO8RdHp-p8QOl2VH2j4O4qhrTUDuvcYIfLy0o-sIHg");

		// Uncomment this section to use JWTs instead
		// HttpBearerAuth bearerAuth = (HttpBearerAuth)
		// defaultClient.getAuthentication("bearerAuth");
		// bearerAuth.setBearerToken("YOUR TOKEN OR JWT");

		System.out.println(basicAuth.getUsername());

		String appId = "607fa87b17ccf300d3bbf7ec"; // String | Identifies the
													// app.
		// ActivitiesApi apiInstance = new ActivitiesApi();
		// ActivityPost activityPost =
		// {"author":{"type":"user","userId":"5963c0d619a30a2e00de36b8"},"type":"conversation:read"};
		// // ActivityPost |

		IntegrationsApi apiInstance = new IntegrationsApi(defaultClient);
		Page ipage = new Page(); // Page | Contains parameters for applying
									// cursor pagination.
		IntegrationListFilter ifilter = new IntegrationListFilter(); // IntegrationListFilter
																		// |
																		// Contains
																		// parameters
																		// for
																		// filtering
																		// the
																		// results.
		// Add required body parameters

		List<Integration> allintegrations = null;
		try {
			IntegrationListResponse result = apiInstance.listIntegrations(appId, ipage, ifilter);
			System.out.println(result);
			allintegrations = result.getIntegrations();
		} catch (ApiException e) {
			System.err.println("Exception when calling IntegrationsApi#listIntegrations");
			System.err.println("Status code: " + e.getCode());
			System.err.println("Reason: " + e.getResponseBody());
			System.err.println("Response headers: " + e.getResponseHeaders());
			// e.printStackTrace();
		}

		for (Integration eachintegration : allintegrations) {

			System.out.println("============================================================");
			System.out.println("============================================================");
			System.out.println("============================================================");

			// whatsapp sandbox
			// String integrationId = "607fa8bdd13abb00d3d4edc4"; // String |
			// The id of the integration.

			// Line
			// String integrationId = "60814e66b8346800d4066634";

			String integrationId = eachintegration.getId();

			System.out.println("Integration Details");
			System.out.println(eachintegration.getDisplayName());
			System.out.println(eachintegration.getStatus());
			System.out.println(eachintegration.getType());
			System.out.println(eachintegration.getDisplayName_JsonNullable());
			System.out.println("---------------------------------------------");

			System.out.println("\n");
			WebhooksApi webhooksApi = new WebhooksApi(defaultClient);
			// Add required body parameters

			WebhookCreateBody webhookCreateBody = new WebhookCreateBody(); // WebhookCreateBody
																			// |
			// Add required body parameters

			try {
				WebhookResponse result = webhooksApi.createWebhook(webhookCreateBody, appId, integrationId);
				System.out.println(result);
			} catch (ApiException e) {
				System.err.println("Exception when calling WebhooksApi#createWebhook");
				System.err.println("Status code: " + e.getCode());
				System.err.println("Reason: " + e.getResponseBody());
				System.err.println("Response headers: " + e.getResponseHeaders());
				// e.printStackTrace();
			}

			System.out.println("\n");
			try {
				WebhookListResponse result = webhooksApi.listWebhooks(appId, integrationId);
				System.out.println(result);
			} catch (ApiException e) {
				System.err.println("Exception when calling WebhooksApi#listWebhooks");
				System.err.println("Status code: " + e.getCode());
				System.err.println("Reason: " + e.getResponseBody());
				System.err.println("Response headers: " + e.getResponseHeaders());
				// e.printStackTrace();
			}

			UsersApi usersapi = new UsersApi(defaultClient);
			String userIdOrExternalId = "+919886087028"; // String | The user's
															// id or externalId.
			// Add required body parameters

			String userId = "";

			System.out.println("\n");
			try {

				UserResponse result = usersapi.getUser(appId, userIdOrExternalId);
				userId = result.getUser().getId();
				System.out.println(result);
			} catch (ApiException e) {
				System.err.println("Exception when calling UsersApi#getUser");
				System.err.println("Status code: " + e.getCode());
				System.err.println("Reason: " + e.getResponseBody());
				System.err.println("Response headers: " + e.getResponseHeaders());
				// e.printStackTrace();
			}

			ConversationsApi conversationsApi = new ConversationsApi(defaultClient);

			ConversationListFilter cfilter = new ConversationListFilter(); // ConversationListFilter
																			// |
																			// Contains
																			// parameters
																			// for
																			// filtering
																			// the
																			// results.
			Page cpage = new Page(); // Page | Contains parameters for applying
										// cursor pagination.
			// Add required body parameters
			// filter.setUserId(userId);
			cfilter.setUserId("+919886087028");

			System.out.println("\n");
			try {
				ConversationListResponse result = conversationsApi.listConversations(appId, cfilter, cpage);
				System.out.println(result);
				return result.toString();
			} catch (ApiException e) {
				System.err.println("Exception when calling ConversationsApi#listConversations");
				System.err.println("Status code: " + e.getCode());
				System.err.println("Reason: " + e.getResponseBody());
				System.err.println("Response headers: " + e.getResponseHeaders());
				// e.printStackTrace();
			}

			//

			ParticipantsApi participantsApi = new ParticipantsApi();

			// String conversationId = 029c31f25a21b47effd7be90; // String |
			// Identifies the conversation.
			// try {
			// //Object result = apiInstance.postActivity(activityPost, appId,
			// conversationId);
			// Object result = participantsApi.
			// System.out.println(result);
			// } catch (ApiException e) {
			// System.err.println("Exception when calling
			// ActivitiesApi#postActivity");
			// //e.printStackTrace();
			// }

			MessagesApi messagesApi = new MessagesApi(defaultClient);
			MessagePost messagePost = new MessagePost(); // MessagePost |

			String conversationId = "";

			System.out.println("\n");
			try {
				MessagePostResponse result = messagesApi.postMessage(messagePost, appId, conversationId);
				System.out.println(result);
			} catch (ApiException e) {
				System.err.println("Exception when calling MessagesApi#postMessage");
				System.err.println("Status code: " + e.getCode());
				System.err.println("Reason: " + e.getResponseBody());
				System.err.println("Response headers: " + e.getResponseHeaders());
				// e.printStackTrace();
			}
		}

		return "Dummy response, shouldn't see this";
	}

	@RequestMapping(value = "/smoochwebhook", method = RequestMethod.POST)
	public String smoochMessagesPostResponse(@RequestBody String body) {

		String appId = "607fa87b17ccf300d3bbf7ec"; // String | Identifies the
		// app.

		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setBasePath("https://api.smooch.io");

		// Configure HTTP basic authorization: basicAuth
		HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
		basicAuth.setUsername("app_607faf3e7db9a300d3ee3ef9");
		basicAuth.setPassword("s72ROpgwUCjih2dyGr3_maCEqTkZ984mX7J4LGQNNRjQnO8RdHp-p8QOl2VH2j4O4qhrTUDuvcYIfLy0o-sIHg");

		System.out.println(body.toString());
		System.out.println(body.getClass().getCanonicalName());

		// LinkedHashMap<String, Object> lhm = (LinkedHashMap<String,
		// Object>)body;
		// ArrayList<String> keys = new ArrayList<>(lhm.keySet());
		//
		// System.out.println("keys " + keys);
		//
		// ArrayList events = (ArrayList)lhm.get("events");
		//
		//
		//
		// System.out.println("events : " + events);
		// System.out.println("events : " +
		// events.getClass().getCanonicalName());
		//
		// Object firstevent = events.get(0);

		try {
			// JSONParser reads the data from string object and break each data
			// into key value pairs
			JSONParser parse = new JSONParser();
			// Type caste the parsed json data in json object
			JSONObject jobj = (JSONObject) parse.parse(body);
			// Store the JSON object in JSON array as objects (For level 1 array
			// element i.e events)
			JSONArray jsonarr_1 = (JSONArray) jobj.get("events");

			System.out.println("jsonarr_1.size()" + jsonarr_1.size());
			// Get data for Results array
			for (int i = 0; i < jsonarr_1.size(); i++) {
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
				System.out.println("\n Payload : " + jsonobj_1.get("payload"));

				JSONObject jsonobj_2 = (JSONObject) jsonobj_1.get("payload");
				System.out.println("\n conversation: " + jsonobj_2.get("conversation"));

				JSONObject jsonobj_3 = (JSONObject) jsonobj_2.get("conversation");
				String id = (String) jsonobj_3.get("id");
				System.out.println("\n conversation id to be used  : " + id);

				JSONObject jsonobj_4 = (JSONObject) jsonobj_2.get("message");
				JSONObject jsonobj_5 = (JSONObject) jsonobj_4.get("content");
				String text = (String) jsonobj_5.get("text");

				MessagesApi messagesApi = new MessagesApi(defaultClient);
				MessagePost messagePost = new MessagePost(); // MessagePost |

				// start the conversation response
				String conversationId = id;

				// prepate the text message
				TextMessage tm = new TextMessage();
				tm.setType("text");
				tm.setText(("Your message was [" + text + "]. Thank you for messaging"));

				Author a = new Author();
				a.setType(Author.TypeEnum.BUSINESS);

				messagePost.setContent(tm);
				messagePost.author(a);

				System.out.println("\n");

				if (!text.startsWith("Your message was")) {
					try {
						MessagePostResponse result = messagesApi.postMessage(messagePost, appId, conversationId);
						System.out.println(result);
					} catch (ApiException e) {
						System.err.println("Exception when calling MessagesApi#postMessage");
						System.err.println("Status code: " + e.getCode());
						System.err.println("Reason: " + e.getResponseBody());
						System.err.println("Response headers: " + e.getResponseHeaders());
						// e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {

		}
		// String payload = body.substring(body.indexOf("payload"));
		// System.out.println(body.substring(payload.indexOf("id") + 4 ,
		// payload.indexOf("type")));
		//

		return "Message received in spring boot appliation";

	}

	@RequestMapping(value = "/smoochiowhatsapptest")
	public String testwhatsappapi() {

		String updatePersonUrl = "https://api.smooch.io/v1.1/apps/607fa87b17ccf300d3bbf7ec/notifications";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("app_607faf3e7db9a300d3ee3ef9",
				"s72ROpgwUCjih2dyGr3_maCEqTkZ984mX7J4LGQNNRjQnO8RdHp-p8QOl2VH2j4O4qhrTUDuvcYIfLy0o-sIHg");

		JSONObject destinationObject = new JSONObject();
		destinationObject.put("integrationId", "6091892e502acf00d353e576");
		destinationObject.put("destinationId", "+91.......");

		JSONObject authorObject = new JSONObject();
		authorObject.put("role", "appMaker");

		JSONObject messageObject = new JSONObject();
		messageObject.put("type", "text");
		messageObject.put("text", "Sample notification message");

		JSONObject requestJsonObject = new JSONObject();
		requestJsonObject.put("destination", destinationObject);
		requestJsonObject.put("author", authorObject);
		requestJsonObject.put("message", messageObject);

		HttpEntity<String> request = new HttpEntity<String>(requestJsonObject.toString(), headers);
		URI locationHeader = restTemplate.postForLocation(updatePersonUrl, request);

		System.out.println("locationHeader in notification api");
		System.out.println(locationHeader);

		return "";

	}

}
