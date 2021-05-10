package com.vikram.linemsgapi.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

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

@RestController
public class FacebookMessengerController {

	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.GET)
	public String facebookMessagesGetResponse(HttpServletRequest request) {

		String token = "EAAN7FuBZC4OUBAFGjanrbHBDTMQ085DyhiZBOwOWe4ULD8nGPAj7d0sJNXauuNUglh1N8DUTF14C8NdjQWjwEU6RI5FakkK3nynHH1uZADXGaQS24lOkdbDuZCJX8oSTir8qFQdk4hU8eGmsMthj2UEXockuwlZBPrCrUIIPiJ9yGZAx2aSukrNzH393cn4HoZD";
		// String VERIFY_TOKEN = "aee8385a-7544-4a97-b751-5334e7ee5178";

		System.out.println("facebookwebhook GET body");

		String hub_challenge = request.getParameter("hub.challenge");

		System.out.println("hub_challenge " + hub_challenge);
		// hub.challenge
		return hub_challenge;
	}

	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.POST)
	public String facebookMessagesPostResponse(@RequestBody String body) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = "EAAN7FuBZC4OUBAFGjanrbHBDTMQ085DyhiZBOwOWe4ULD8nGPAj7d0sJNXauuNUglh1N8DUTF14C8NdjQWjwEU6RI5FakkK3nynHH1uZADXGaQS24lOkdbDuZCJX8oSTir8qFQdk4hU8eGmsMthj2UEXockuwlZBPrCrUIIPiJ9yGZAx2aSukrNzH393cn4HoZD";

		String send_message_url = "https://graph.facebook.com/v10.0/me/messages?access_token=" + token;

		System.out.println("facebookwebhook POST body");
		System.out.println(body);

		try {
			// JSONParser reads the data from string object and break each data
			// into key value pairs
			JSONParser parse = new JSONParser();
			// Type caste the parsed json data in json object
			JSONObject jobj = (JSONObject) parse.parse(body);

			String object = (String) jobj.get("object");

			JSONObject jsonobj_2 = (JSONObject) jobj.get("message");
			String text = (String) jsonobj_2.get("text");

			JSONArray jsonarr_1 = (JSONArray) jobj.get("entry");

			System.out.println("jsonarr_1.size()" + jsonarr_1.size());
			// Get data for Results array
			for (int i = 0; i < jsonarr_1.size(); i++) {
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

				JSONArray jsonarr_2 = (JSONArray) jobj.get("messaging");

				if (jsonarr_2 != null && jsonarr_2.size() > 0) {

					
					JSONObject jsonobj_3 = (JSONObject) jsonarr_2.get(0);
					
					JSONObject jsonobj_4 = (JSONObject)jsonobj_3.get("sender");
	
					JSONObject jsonobj_5 = (JSONObject)jsonobj_3.get("message");
					String textfrommessage = (String) jsonobj_5.get("text");
					
					JSONObject messageObject = new JSONObject();
					messageObject.put("text", "Reply to your message[" + textfrommessage + "]. Thank you for messaging.");
				

					JSONObject requestJsonObject = new JSONObject();
					requestJsonObject.put("recipient", jsonobj_4);
					requestJsonObject.put("messaging_type", "text");
					// requestJsonObject.put("sender", senderObject);
					requestJsonObject.put("message", messageObject);

					HttpEntity<String> request = new HttpEntity<String>(requestJsonObject.toString(), headers);
					URI locationHeader = restTemplate.postForLocation(send_message_url, request);

					System.out.println("locationHeader in facebook webhook api");
					System.out.println(locationHeader);
				}
			}
		} catch (Exception e) {
			
			System.out.println(e.getMessage());

		}

		return "";
	}
}
