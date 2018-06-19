package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	
	private List<Task> tasks;
	
	public Scheduler() {
		this(new Fraction(1,1));
	}
	
	public Scheduler(Fraction fudgeRatio) {
		this.tasks = new ArrayList<Task>();
	}
	
	public void addTask(Task t) {
		tasks.add(t);
	}
	
	public boolean removeTask(Task t) {
		return tasks.remove(t);
	}
	
	public Schedule schedule(TimeSlot ts, TaskSelector selector) {
		return new Schedule(new TaskProvider(new ArrayList<Task>(tasks),selector), ts);
	}
	
	public static final TaskSelector dumbSelector = new TaskSelector(){
		@Override
		public Task select(List<Task> tasks, Duration remaining) {
			tasks.sort((a,b)->a.getDue().compareTo(b.getDue()));
			for(Task task : tasks){
				if(task.getDuration().compareTo(remaining) <= 0) return task;
			}
			return null;
		}
	};
}
