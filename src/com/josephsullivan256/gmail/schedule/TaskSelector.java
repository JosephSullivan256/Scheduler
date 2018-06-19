package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.util.List;

public interface TaskSelector {
	
	public Task select(List<Task> tasks, Duration remaining);
}
