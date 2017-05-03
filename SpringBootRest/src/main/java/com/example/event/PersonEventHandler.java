package com.example.event;

import com.example.domain.Person;
import org.apache.log4j.Logger;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Person.class)
public class PersonEventHandler {

	private static final Logger logger = Logger.getLogger(PersonEventHandler.class);
	
	@HandleBeforeCreate
	public void handlePersonCreate(Person p){
		logger.info("=========================================HandleBeforeCreate");
	}
}

