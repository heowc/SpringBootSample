package com.tistory.heowc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private final Long idx;
    private final String content;
}
