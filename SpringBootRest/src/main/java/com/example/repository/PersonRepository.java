package com.example.repository;

import java.util.List;

import com.example.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {

	@RestResource(path="lastname", rel="people", exported=true)
	List<Person> findByLastName(@Param("lastName") String lastName);

	@RestResource(path="firstname", rel="people", exported=false)
	List<Person> findByFirstName(@Param("firstName") String firstName);
	
}



