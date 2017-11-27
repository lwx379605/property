package com.lmc.property.event;

import org.springframework.context.ApplicationEvent;

public class BaseEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4758283796167735773L;

	public BaseEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
