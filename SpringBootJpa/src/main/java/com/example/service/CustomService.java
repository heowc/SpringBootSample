package com.example.service;

import com.example.domain.Customer;

public interface CustomService {

    public Customer insert(Customer customer);
    public Customer update(Customer customer);
    public Customer find(Long idx);
    public Customer delete(Customer customer);
}
