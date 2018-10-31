package com.example.event;

import com.example.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Person.class)
public class PersonEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(PersonEventHandler.class);
	
	@HandleBeforeCreate
	public void handlePersonCreate(Person p){
		logger.info("========================================= HandleBeforeCreate");
		// validation logic...
	}
}

