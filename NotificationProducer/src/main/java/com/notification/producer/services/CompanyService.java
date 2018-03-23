package com.notification.producer.services;

import com.notification.models.Company;
import com.notification.models.NotifyMessage;

public interface CompanyService {
	public void setCompanyItems(Company comp);
	public void setMessage(NotifyMessage message);

}
