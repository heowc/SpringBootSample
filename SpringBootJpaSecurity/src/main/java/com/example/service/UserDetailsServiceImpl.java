package com.example.service;

import com.example.config.security.UserDetailsImpl;
import com.example.domain.User;
import com.example.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String id) {
        User user = repository.findById(id);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Not Found : %s", id));
        }

        return new UserDetailsImpl(user);
    }
}
