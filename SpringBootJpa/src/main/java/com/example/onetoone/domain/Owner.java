package com.example.onetoone.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "OWNER")
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

    @Column(name = "OWNER_NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private Market market;

    protected Owner() { }

    public Owner(String name) {
        this.name = name;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", market=" + market +
                '}';
    }
}
