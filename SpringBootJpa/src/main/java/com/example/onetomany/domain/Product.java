package com.example.onetomany.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PRODUCT")
@GenericGenerator(
        name = "ProductSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
               @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PRODUCT_SEQ"),
               @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
               @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Product {

    @Id @GeneratedValue(generator = "ProductSequenceGenerator")
    @Column(name = "PRODUCT_IDX")
    private Long idx;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();

    protected Product() { }

	public Product(String name, String content) {
		this.name = name;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Product{" +
				"idx=" + idx +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				", orders=" + orders +
				'}';
	}
}
