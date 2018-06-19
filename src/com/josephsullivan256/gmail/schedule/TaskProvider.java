package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TaskProvider {
	
	private List<Task> remainingTasks;
	private List<Task> tasksToRemove;
	
	private TaskSelector selector;
	
	public TaskProvider(List<Task> tasks, TaskSelector selector){
		this.remainingTasks = tasks;
		this.tasksToRemove = new ArrayList<Task>();
		
		this.selector = selector;
	}
	
	public Task next(Duration remaining){
		Task temp = selector.select(remainingTasks, remaining);
		remainingTasks.remove(temp);
		tasksToRemove.add(temp);
		return temp;
	}
	
	public void restore(Task t){
		remainingTasks.add(t);
		tasksToRemove.remove(t);
	}
}
