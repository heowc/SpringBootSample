package com.example.onetoone.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MARKET")
@GenericGenerator(
		name = "MarketSequenceGenerator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@org.hibernate.annotations.Parameter(name = "sequence_name", value = "MARKET_SEQ"),
				@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
				@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
		}
)
public class Market {

	@Id
	@GeneratedValue(generator = "MarketSequenceGenerator")
	@Column(name = "MARKET_ID")
	private Long idx;

	@Column(name = "MARKET_NAME")
	private String name;

	@Column(name = "MARKET_LOCATION")
	private String location;

	@OneToOne
	@JoinColumn(name = "MARKET_ID")
	private Owner owner;

	protected Market() { }

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
				", owner=" + owner +
				'}';
	}
}
