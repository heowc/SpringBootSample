package com.example.java.onetoone.domain;

import javax.persistence.*;

@Entity
@Table(name = "MARKET")
public class Market {

    @Id
    @GeneratedValue
    @Column(name = "MARKET_ID")
    private Long idx;

    @Column(name = "MARKET_NAME")
    private String name;

    @Column(name = "MARKET_LOCATION")
    private String location;

    @OneToOne
    @JoinColumn(name = "MARKET_ID")
    private Owner owner;

    protected Market() {
    }

    public Market(String name, String location) {
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Market{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", owner=" + owner.getName() +
                '}';
    }
}
