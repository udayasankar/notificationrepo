package com.notification.broker.service;

import com.notification.model.NotifyMessage;

public interface BrokerMessageService {
	
	public void sendMessage(NotifyMessage msg);
	public void addMessage(NotifyMessage msg);

}
