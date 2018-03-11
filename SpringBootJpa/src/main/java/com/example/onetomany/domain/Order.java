package com.example.onetomany.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TB_ORDER")
@GenericGenerator(
		name = "OrderSequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDER_SEQ"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
		}
)
public class Order {

	@Id @GeneratedValue(generator = "OrderSequenceGenerator")
	@Column(name = "ORDER_IDX")
	private Long idx;

	@Column(name = "PRODUCT_COUNT")
	private Integer productCount;

	@Column(name = "BIGO")
	private String bigo;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_IDX")
	private Product product;

	protected Order() { }

	public Order(Integer productCount, String bigo, Product product) {
		this.productCount = productCount;
		this.bigo = bigo;
		this.product = product;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public String getBigo() {
		return bigo;
	}

	public void setBigo(String bigo) {
		this.bigo = bigo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order{" +
				"idx=" + idx +
				", productCount=" + productCount +
				", bigo='" + bigo + '\'' +
				", product=" + product +
				'}';
	}
}
