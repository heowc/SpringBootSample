package com.example.java.simple.repository;

import com.example.java.simple.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
