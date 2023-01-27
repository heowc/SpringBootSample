package com.example.java.simple.repository;

import com.example.java.simple.domain.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class SimpleCustomJpqlRepository implements CustomJpqlRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findByName(String name) {
        return em.createNamedQuery("Custom.findByName", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }
}
