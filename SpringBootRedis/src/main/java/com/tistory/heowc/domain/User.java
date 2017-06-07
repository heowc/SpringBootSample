package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String name;
    private Integer age;

    public static User getDefaultUser() {
        return new User("wonchul", 26);
    }
}
