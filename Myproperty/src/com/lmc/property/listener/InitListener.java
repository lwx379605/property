package com.lmc.property.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitListener{

	@EventListener
	public void hander(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("db cache init");
	}
}
