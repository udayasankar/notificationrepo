package com.notification.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
 
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Product.class)
public class Product {
    private String name;
	private String productPrice;
	private String offerPrice;
    private Company company;
	
    public Product(){
    }
    
    public Product(String name,String offerPrice){
    	this.name = name;
    	this.offerPrice=offerPrice;
    }
    
    
    public Product(String name, Company company){
    	this.name = name;
    	this.company = company;
    }
    
    // name
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // products
    public void setCompany(Company company){
    	this.company = company;
    }
    
    public Company getCompany(){
    	return this.company;
    }
    
    public String getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}
	
	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
}