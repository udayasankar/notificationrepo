package com.notification.subscriber.servicesimpl;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.notification.models.Company;
import com.notification.models.NotifyMessage;
import com.notification.models.Product;
import com.notification.subscriber.services.MessageService;



@Component
public class MessageServiceImpl implements MessageService{
	public void sendSlackMessage(Company message) 
    {
		RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("channel", "notificationchannel");
        String notiMessage="Company Name :"+message.getName()+" ";
        for(Product products:message.getProducts())
        	notiMessage=notiMessage+" Product Name : "+products.getName()+ " Product Price : "+products.getProductPrice()+
        			" Product OfferPrice : "+products.getOfferPrice();
        map.put("text", notiMessage);
        HttpEntity<HashMap> request = new HttpEntity<HashMap>(map);
        ResponseEntity<String> responseEntity = null;
        try {
              responseEntity = restTemplate.exchange("https://hooks.slack.com/services/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",
            		  HttpMethod.POST, request, String.class);

        } catch (HttpClientErrorException e) {

               if (e.getStatusCode() == HttpStatus.NOT_FOUND)

                     responseEntity = new ResponseEntity<String>(

                                   e.getResponseBodyAsString(), HttpStatus.NOT_FOUND);

        }catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
        }
    }

	@Override
	public void sendBrokerSlackMessage(NotifyMessage message) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("channel", "notificationchannel");
        String notiMessage="Company Name :"+message.getCompany().getName()+" ";
        for(Product products:message.getProducts())
        	notiMessage=notiMessage+" Product Name : "+products.getName()+ " Product Price : "+products.getProductPrice()+
        			" Product OfferPrice : "+products.getOfferPrice();
        map.put("text", notiMessage);
        HttpEntity<HashMap> request = new HttpEntity<HashMap>(map);
        ResponseEntity<String> responseEntity = null;
        try {
              responseEntity = restTemplate.exchange("https://hooks.slack.com/services/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",
            		  HttpMethod.POST, request, String.class);

        } catch (HttpClientErrorException e) {

               if (e.getStatusCode() == HttpStatus.NOT_FOUND)

                     responseEntity = new ResponseEntity<String>(

                                   e.getResponseBodyAsString(), HttpStatus.NOT_FOUND);

        }catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
        }
		
	}

}
