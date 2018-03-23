package com.notification.producer.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.notification.producer.jms.JmsPublisher;
import com.notification.producer.services.CompanyService;

@Component
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	JmsPublisher publisher;

	public void setCompanyItems(Company comp) {
		// TODO Auto-generated method stub
	            				
				List<Product> compProduct=comp.getProducts();
				List<Product> compListProducts=new ArrayList<Product>();
				for(Product pro:compProduct)
				{
					Product product = new Product(pro.getName(),pro.getProductPrice(),pro.getOfferPrice());
					compListProducts.add(product);
				}
				Company compSend = new Company(comp.getName(), compListProducts);
				publisher.send(compSend);
		
		
	}

	@Override
	public void setMessage(NotifyMessage message) {
		String URL="http://localhost:8091/sendmessage";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<NotifyMessage> httpEntity=new HttpEntity<>(message);
		ResponseEntity<String> responseEntity = null;
         try {

               responseEntity = restTemplate.exchange(URL,HttpMethod.POST, httpEntity, String.class);

         } catch (HttpClientErrorException e) {

           
               if (e.getStatusCode() == HttpStatus.NOT_FOUND)

                      responseEntity = new ResponseEntity<String>(e.getResponseBodyAsString(), HttpStatus.NOT_FOUND);

         }
		
	}

}
