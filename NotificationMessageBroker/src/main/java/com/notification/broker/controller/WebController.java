package com.notification.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notification.broker.service.BrokerMessageService;
import com.notification.model.NotifyMessage;

@RestController
public class WebController {
	
	@Autowired
	private BrokerMessageService brokerService;
	
       @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
	   public ResponseEntity < String > persistPerson(@RequestBody NotifyMessage msg) {
		
	    	if(msg!=null)
	    	{
	    		brokerService.addMessage(msg);
	    		brokerService.sendMessage(msg);
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	    	}
	    	else
	    		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
	    		
	    }


}
