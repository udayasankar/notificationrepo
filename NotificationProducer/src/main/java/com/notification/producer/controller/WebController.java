package com.notification.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notification.models.Company;
import com.notification.models.NotifyMessage;
import com.notification.producer.services.CompanyService;

@RestController
public class WebController {
	
	@Autowired
	private CompanyService companyService;
	
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ResponseEntity < String > persistPerson(@RequestBody Company company) {
		
	    	if(company!=null)
	    	{
    	      companyService.setCompanyItems(company);
	          return ResponseEntity.status(HttpStatus.CREATED).build();
	    	}
	    	else
	    		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
	    		
	    }
    
    @RequestMapping(value = "/publishbroker", method = RequestMethod.POST)
   	public ResponseEntity < String > publishBroker(@RequestBody NotifyMessage message) {
   		
   	    	if(message!=null)
   	    	{
       	      companyService.setMessage(message);
   	          return ResponseEntity.status(HttpStatus.CREATED).build();
   	    	}
   	    	else
   	    		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
   	    		
   	    }


}
