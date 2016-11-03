package com.example.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OfficeRepository extends CrudRepository<Office, Long> {

	List<Office> findByNameIn(String[] names);
}
