package com.heowc.account;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Configurable(autowire = Autowire.BY_TYPE)
@Entity
@Access(AccessType.FIELD)
public class Account {

    @Id
    private String id;

    private String password;

    @Transient
    @Autowired
    private PasswordEncoder passwordEncoder;

    protected Account() { }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword() {
        this.password = RandomStringUtils.randomAlphanumeric(10);
    }
}
