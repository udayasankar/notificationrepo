package com.notification.subscriber.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.notification.models.Company;
import com.notification.subscriber.services.MessageService;

@Component
public class JmsSubcriber {
	
	@Autowired
	private MessageService messageService;
	
	@JmsListener(destination = "${jsa.activemq.topic}")
	public void receive(Company msg){
		System.out.println("Recieved Message: " + msg);
		messageService.sendSlackMessage(msg);
		//messageService.sendMailMessage(msg);
	}
	
		
}
