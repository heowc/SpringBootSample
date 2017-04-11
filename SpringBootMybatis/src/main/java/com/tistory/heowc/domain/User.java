package com.tistory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long idx;
    private String name;
    private String local ;
}
