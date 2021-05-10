package com.vikram.linemsgapi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookMessengerController {
	
	@RequestMapping(value = "/facebookwebhook", method = RequestMethod.POST)
	public String smoochMessagesPostResponse(@RequestBody String body) {
		
		System.out.println("facebookwebhook body");
		System.out.println(body);
		
		return "";
	}
}
