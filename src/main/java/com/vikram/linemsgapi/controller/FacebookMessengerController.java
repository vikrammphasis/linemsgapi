package com.vikram.linemsgapi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookMessengerController {
	
	
	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.GET)
	public String facebookMessagesGetResponse(@RequestBody String body) {
		
		String VERIFY_TOKEN = "aee8385a-7544-4a97-b751-5334e7ee5178";
		
		System.out.println("facebookwebhook GET body");
		System.out.println(body);
		
		return "";
	}
	
	
	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.POST)
	public String facebookMessagesPostResponse(@RequestBody String body) {
		
		String VERIFY_TOKEN = "aee8385a-7544-4a97-b751-5334e7ee5178";
		
		System.out.println("facebookwebhook POST body");
		System.out.println(body);
		
		return "";
	}
}
