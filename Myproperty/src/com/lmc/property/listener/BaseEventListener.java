package com.lmc.property.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lmc.property.event.BaseEvent;

@Component
public class BaseEventListener {
	
	@EventListener
	public void demoListener(BaseEvent evt ){
		System.out.println(evt.getSource());
	}
}
