package com.lmc.property.listener;

import java.util.logging.Logger;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitListener{
	
	private static final Logger LOGGER = Logger.getLogger(InitListener.class.getName());
	
	@EventListener
	public void hander(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		LOGGER.info("===========db cache init================");
	}
}
