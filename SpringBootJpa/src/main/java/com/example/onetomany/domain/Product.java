package com.example.onetomany.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TB_PRODUCT")
@RequiredArgsConstructor
@GenericGenerator(
        name = "ProductSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
               @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PRODUCT_SEQ"),
               @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
               @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Product implements Serializable {

    @Id @GeneratedValue(generator = "ProductSequenceGenerator")
    @Column(name = "PRODUCT_IDX")
    private Long idx;

    @Column(name = "NAME") @NonNull
    private String name;

    @Column(name = "CONTENT") @NonNull
    private String content;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    protected Product() {}
}
