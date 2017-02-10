package com.tistory.heowc.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public Date nowToDate() {
		return Date.from( now().toInstant(ZoneOffset.ofHours(9)) );
	}
	
	public Date nowAfterDaysToDate(Long days) {
		return Date.from( nowAfterDays(days).toInstant(ZoneOffset.ofHours(9)) );
	}
	
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
	
	public LocalDateTime nowAfterDays(Long days) {
		return now().plusDays(days);
	}
}
