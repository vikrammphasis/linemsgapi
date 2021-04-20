package com.vikram.linemsgapi.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoMsgController {
	
	@RequestMapping(value = "/postdata",method = RequestMethod.POST)
    public String PostData(){

       return "{\n" +
               "   \"value\":\"4\",\n" +
               "   \"name\":\"David\"\n" +
               "}";
    }

    @RequestMapping(value = "/kakaotest")
    public String getPostResponse(){
    	
    	String token = "";
    	RestTemplate restTemplate = new RestTemplate();

    	HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        //kakao
        headers.setBearerAuth(token);
        
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        
        //return restTemplate.exchange("http://localhost:8080/postdata",HttpMethod.POST,entity,String.class).getBody();

    
        return restTemplate.exchange("https://kapi.kakao.com/v1/api/talk/profile",HttpMethod.GET,entity,String.class).getBody();
    }

}
