package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Notice implements Serializable {

    @Id
    @GeneratedValue
    private Long idx;
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    protected Notice() {
    }
}
