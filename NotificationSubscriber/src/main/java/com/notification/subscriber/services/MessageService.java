package com.notification.subscriber.services;

import com.notification.models.Company;
import com.notification.models.NotifyMessage;

public interface MessageService {
	public void sendSlackMessage(Company message);
	public void sendBrokerSlackMessage(NotifyMessage message);

}
