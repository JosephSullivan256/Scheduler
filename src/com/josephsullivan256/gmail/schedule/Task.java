package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
	
	private String description;
	private LocalDateTime due;
	private Duration duration;
	
	private List<Tag> tags;
	
	public Task(String description, LocalDateTime due, Duration duration) {
		this.description = description;
		this.due = due;
		this.duration = duration;
		this.tags = new ArrayList<Tag>();
	}
	
	public void addTag(Tag t) {
		this.tags.add(t);
		t.register(this);
	}
	
	public String getDescription() { return description; }
	
	public LocalDateTime getDue() { return due; }
	
	public Duration getDuration() { return duration; }
	
	public List<Tag> getTags() { return tags; }
	
	public boolean hasTag(Tag tag1) { return tags.contains(tag1); }
	
	@Override
	public String toString() {
		return description + "\n" + due.toString() + "\n" + duration.toString();
	}
}
