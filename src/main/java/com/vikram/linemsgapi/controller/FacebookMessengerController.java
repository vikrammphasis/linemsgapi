package com.vikram.linemsgapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookMessengerController {

	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.GET)
	public String facebookMessagesGetResponse(HttpServletRequest request) {

		String token = "EAAN7FuBZC4OUBAFGjanrbHBDTMQ085DyhiZBOwOWe4ULD8nGPAj7d0sJNXauuNUglh1N8DUTF14C8NdjQWjwEU6RI5FakkK3nynHH1uZADXGaQS24lOkdbDuZCJX8oSTir8qFQdk4hU8eGmsMthj2UEXockuwlZBPrCrUIIPiJ9yGZAx2aSukrNzH393cn4HoZD";
		String VERIFY_TOKEN = "aee8385a-7544-4a97-b751-5334e7ee5178";

		System.out.println("facebookwebhook GET body");
		
		
		String hub_challenge = request.getParameter("hub.challenge");

		System.out.println("hub_challenge " + hub_challenge);
		//hub.challenge
		return hub_challenge;
	}

	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.POST)
	public String facebookMessagesPostResponse(@RequestBody String body) {

		String VERIFY_TOKEN = "aee8385a-7544-4a97-b751-5334e7ee5178";

		System.out.println("facebookwebhook POST body");
		System.out.println(body);

		return "";
	}
}
