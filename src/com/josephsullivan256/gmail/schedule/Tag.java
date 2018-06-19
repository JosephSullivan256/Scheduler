package com.josephsullivan256.gmail.schedule;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	
	private List<Task> tasks;
	
	public Tag() {
		this.tasks = new ArrayList<Task>();
	}
	
	public void register(Task t) {
		tasks.add(t);
	}
	
	public void forget(Task t) {
		tasks.remove(t);
	}
}