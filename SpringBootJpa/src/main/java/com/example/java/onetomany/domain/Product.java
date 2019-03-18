package com.example.java.onetomany.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_PRODUCT")
public class Product {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_IDX")
    private Long idx;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

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

	@Override
	public String toString() {
		return "Product{" +
				"idx=" + idx +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
