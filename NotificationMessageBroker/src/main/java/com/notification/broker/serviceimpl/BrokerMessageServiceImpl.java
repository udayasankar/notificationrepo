package com.notification.broker.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.notification.broker.service.BrokerMessageService;
import com.notification.model.NotifyMessage;

@Component
public class BrokerMessageServiceImpl implements BrokerMessageService{
	private ConcurrentHashMap<String,ArrayList<String>> conMapCustomer=new ConcurrentHashMap<String,ArrayList<String>>();
	private ConcurrentHashMap<String, Queue<NotifyMessage>> conHashMap=new ConcurrentHashMap<String,Queue<NotifyMessage>>();

	@Override
	public void sendMessage(NotifyMessage msg) {
		  String URL="http://localhost:8090/brokersubscribe";
		  Queue<NotifyMessage> queueValue=conHashMap.get(msg.getTopicName());
		  RestTemplate restTemplate = new RestTemplate();
		  HttpEntity<NotifyMessage> httpEntity=new HttpEntity<>(queueValue.remove());
		  ResponseEntity<String> responseEntity = null;
           try {

                 responseEntity = restTemplate.exchange(URL,HttpMethod.POST, httpEntity, String.class);

           } catch (HttpClientErrorException e) {

             
                 if (e.getStatusCode() == HttpStatus.NOT_FOUND)

                        responseEntity = new ResponseEntity<String>(e.getResponseBodyAsString(), HttpStatus.NOT_FOUND);

           }


    }

	
	
	public void addMessage(NotifyMessage msg) {
		// TODO Auto-generated method stub
		if(!conHashMap.containsKey(msg.getTopicName()))
		{
			Queue<NotifyMessage> queueValue=new LinkedList<NotifyMessage>();
			queueValue.add(msg);
			conHashMap.put(msg.getTopicName(), queueValue);
		}
		else
		{
			Queue<NotifyMessage> queueValue=conHashMap.get(msg.getTopicName());
			queueValue.add(msg);
			conHashMap.put(msg.getTopicName(), queueValue);
			
		}
		
	}

}
