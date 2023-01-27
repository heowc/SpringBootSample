package com.heowc.domain;

import com.heowc.event.PasswordChangedEvent;
import org.springframework.context.ApplicationEventPublisher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.security.SecureRandom;

@Entity
public class Member {

    @Id
    private String id;

    private String password;

    protected Member() {}

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password, ApplicationEventPublisher publisher) {
        if (!this.password.matches(password)) {
            throw new PasswordNotMatchingException();
        } else {
            this.password = generatePassword();
            publisher.publishEvent(new PasswordChangedEvent(this, this.id));
        }
    }

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        return Integer.toHexString(random.nextInt());
    }
}
