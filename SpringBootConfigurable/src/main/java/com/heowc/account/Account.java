package com.heowc.account;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowire;
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
    private PasswordEncoder passwordEncoder;

    protected Account() { }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword() {
        String newPassword = RandomStringUtils.randomAlphanumeric(10);
        this.password = passwordEncoder.encode(newPassword);
    }
}
