package com.example.java.simple.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TimeData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idx;

	private LocalDateTime date;

	protected TimeData() { }

	public TimeData(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TimeData{" +
				"idx=" + idx +
				", date=" + date +
				'}';
	}
}
