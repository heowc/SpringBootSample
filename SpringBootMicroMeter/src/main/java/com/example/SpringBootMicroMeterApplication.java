package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMicroMeterApplication {

	// Meter set:
	// Timer, Counter, Gauge, DistributionSummary, LongTaskTimer, FunctionCounter, FunctionTimer, and TimeGauge
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroMeterApplication.class, args);
	}
}
