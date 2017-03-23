package com.example.event;

import org.apache.log4j.Logger;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.example.domain.Person;

@RepositoryEventHandler(Person.class)
public class PersonEventHandler {

	private static final Logger logger = Logger.getLogger(PersonEventHandler.class);
	
	@HandleBeforeCreate
	public void handlePersonCreate(Person p){
		logger.info("=========================================HandleBeforeCreate");
	}
}

