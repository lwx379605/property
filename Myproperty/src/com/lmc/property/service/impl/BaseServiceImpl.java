package com.lmc.property.service.impl;

import javax.inject.Inject;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.lmc.property.event.BaseEvent;
import com.lmc.property.service.BaseService;

@Service
public class BaseServiceImpl  implements BaseService{
	
	@Inject
	ApplicationEventPublisher publisher;
	
	public void publishEvent() {
		publisher.publishEvent(new BaseEvent(this));
	}

	@Override
	public String toString() {
		return "BaseServiceImpl [publisher=" + publisher + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
