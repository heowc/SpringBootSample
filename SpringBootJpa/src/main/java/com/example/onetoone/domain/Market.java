package com.example.onetoone.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MARKET")
@RequiredArgsConstructor
@GenericGenerator(
        name = "MarketSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MARKET_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Market implements Serializable {

    @Id @GeneratedValue(generator = "MarketSequenceGenerator")
    @Column(name = "MARKET_ID")
    private Long idx;

    @Column(name = "MARKET_NAME") @NonNull
    private String name;

    @Column(name = "MARKET_LOCATION") @NonNull
    private String location;

    @OneToOne
    @JoinColumn(name = "MARKET_ID")
    private Owner owner;

    protected Market() {}
}
