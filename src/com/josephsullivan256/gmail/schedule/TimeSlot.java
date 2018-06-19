package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSlot {
	
	private LocalDateTime start;
	private Duration duration;
	
	public TimeSlot(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.duration = Duration.between(start, end);
	}
	
	public TimeSlot(LocalDateTime start, Duration duration) {
		this.start = start;
		this.duration = duration;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public Duration getDuration() {
		return duration;
	}
}
