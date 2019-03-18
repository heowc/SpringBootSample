package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
