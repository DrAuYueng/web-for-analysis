package com.acm.bootstrap.events;

import org.springframework.context.ApplicationEvent;

public class CloudConfigApplicationEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    public CloudConfigApplicationEvent(Object source) {
        super(source);
        // TODO Auto-generated constructor stub
    }

}
