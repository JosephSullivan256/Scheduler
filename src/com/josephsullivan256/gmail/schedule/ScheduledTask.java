package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduledTask {
	
	private Task task;
	private LocalDateTime start;
	private Duration realizedDuration;
	
	public ScheduledTask(Task task, LocalDateTime start, Duration realizedDuration) {
		this.task = task;
		this.start = start;
		this.realizedDuration = realizedDuration;
	}
	
	public Task getTask() {
		return task;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public void shift(Duration d) {
		this.start = start.minus(d);
	}
	
	public Duration getRealizedDuration() {
		return realizedDuration;
	}
	
	public void extend(Duration d) {
		realizedDuration = realizedDuration.plus(d);
	}
}
