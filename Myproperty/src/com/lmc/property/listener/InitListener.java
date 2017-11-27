package com.lmc.property.listener;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lmc.property.service.ConfigService;


@Component
public class InitListener{
	
	private static final Logger LOGGER = Logger.getLogger(InitListener.class.getName());
	@Inject
	private ConfigService configService;
	
	@EventListener
	public void hander(ContextRefreshedEvent contextRefreshedEvent) {
		if (contextRefreshedEvent.getApplicationContext() == null || contextRefreshedEvent.getApplicationContext().getParent() != null) {
			return;
		}
		LOGGER.info("===========初始化加载================");
		configService.init();
		// TODO Auto-generated method stub
		
	}
}
