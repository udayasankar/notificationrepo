package com.notification.subscriber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notification.models.NotifyMessage;
import com.notification.subscriber.services.MessageService;

@RestController
public class WebController {
	
	@Autowired
	private MessageService messageService;
	
       @RequestMapping(value = "/brokersubscribe", method = RequestMethod.POST)
	   public ResponseEntity < String > persistPerson(@RequestBody NotifyMessage msg) {
		
	    	if(msg!=null)
	    	{
	    		messageService.sendBrokerSlackMessage(msg);
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	    	}
	    	else
	    		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
	    		
	    }


}
