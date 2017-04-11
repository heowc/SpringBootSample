package com.tistory.heowc.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private Long idx;
    private String name;
    private String local ;
}
