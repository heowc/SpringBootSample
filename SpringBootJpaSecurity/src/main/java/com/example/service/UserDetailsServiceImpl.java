package com.example.service;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String id) {
        User user = repository.findById(id);

        if (user == null) {
            throw new UsernameNotFoundException(id);
        }

        return new UserDetailsImpl(user);
    }
}
