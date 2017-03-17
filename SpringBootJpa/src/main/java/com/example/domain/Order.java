package com.example.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_ORDER")
@RequiredArgsConstructor
@GenericGenerator(
        name = "OrderSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDER_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Order implements Serializable {

    @Id @GeneratedValue(generator = "OrderSequenceGenerator")
    @Column(name = "ORDER_IDX")
    private Long idx;

    @Column(name = "PRODUCT_IDX") @NonNull
    private Long productIdx;

    @Column(name = "PRODUCT_COUNT") @NonNull
    private Integer productCount;

    @Column(name = "BIGO") @NonNull
    private String bigo;

    @ManyToOne
    private Product product;
}
