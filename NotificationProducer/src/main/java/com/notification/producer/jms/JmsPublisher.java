package com.notification.producer.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.notification.models.Company;

@Component
public class JmsPublisher {
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${jsa.activemq.topic}")
	String topic;
	
	public void send(Company apple){
		jmsTemplate.convertAndSend(topic, apple);
	}
}
