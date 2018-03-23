package com.notification.models;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = NotifyMessage.class)
public class NotifyMessage {

	private String topicName;
	private Company company;
	private List<Product> products;
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String toString(){
		JSONObject jsonInfo = new JSONObject();
		
		try {
			jsonInfo.put("name", company.getName());
 
			JSONArray productArray = new JSONArray();
			if (this.products != null) {
				this.products.forEach(product -> {
					JSONObject subJson = new JSONObject();
					try {
						subJson.put("name", product.getName());
					} catch (JSONException e) {}
					
					productArray.put(subJson);
				});
			}
			jsonInfo.put("products", productArray);
		} catch (JSONException e1) {}
		return jsonInfo.toString();
	}

}
