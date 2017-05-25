package com.example.simple.service;

import com.example.simple.domain.Customer;
import com.example.simple.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer find(Long idx) {
        return customerRepository.findOne(idx);
    }

    @Override
    public Customer delete(Customer customer) {
        customerRepository.delete(customer);
        return customer;
    }
}
