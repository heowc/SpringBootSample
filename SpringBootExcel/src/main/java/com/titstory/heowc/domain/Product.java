package com.titstory.heowc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private String uniqueId;

    private String name;

    private String comment;

}
