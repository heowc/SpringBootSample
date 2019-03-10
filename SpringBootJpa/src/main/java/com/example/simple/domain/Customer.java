package com.example.simple.domain;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@NamedQuery(
        name = "Custom.findByName",
        query = "SELECT c FROM Customer c WHERE c.name = :name ")
public class Customer {

    @Id
    @GeneratedValue
    private Long idx;

    @Column(length = 50)
    private String name;

    @Column(length = 14)
    private String tel;

    private String bigo;

    protected Customer() { }

    public Customer(String name, String tel, String bigo) {
        this.name = name;
        this.tel = tel;
        this.bigo = bigo;
    }

    public Long getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getBigo() {
        return bigo;
    }

    public void changeBigo(String bigo) {
        this.bigo = bigo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", bigo='" + bigo + '\'' +
                '}';
    }
}
