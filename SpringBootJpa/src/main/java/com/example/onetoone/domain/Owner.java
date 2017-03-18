package com.example.onetoone.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "OWNER")
@RequiredArgsConstructor
@GenericGenerator(
        name = "OwnerSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "OWNER_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Owner {

    @Id @GeneratedValue(generator = "OwnerSequenceGenerator")
    @Column(name = "OWNER_ID")
    private Long idx;

    @Column(name = "OWNER_NAME") @NonNull
    private String name;

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private Market market;
}
