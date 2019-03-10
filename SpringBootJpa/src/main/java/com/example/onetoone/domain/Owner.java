package com.example.onetoone.domain;

import javax.persistence.*;

@Entity
@Table(name = "OWNER")
public class Owner {

    @Id @GeneratedValue
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
