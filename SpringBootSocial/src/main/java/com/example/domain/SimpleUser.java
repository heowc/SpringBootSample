package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
public class SimpleUser implements Serializable {

    @Id
    private Long idx;

    private String email;
}
