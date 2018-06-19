package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	
	private List<ScheduledTask> tasks;
	private TaskProvider provider;
	private TimeSlot ts;
	
	private LocalDateTime nextAvailable;
	
	public Schedule(TaskProvider provider, TimeSlot ts) {
		this.tasks = new ArrayList<ScheduledTask>();
		this.provider = provider; // TODO make it fill task list using provider
		this.ts = ts;
		this.nextAvailable = ts.getStart();
		
		Task temp = provider.next(remaining());
		while(temp != null){
			pushBack(temp);
			temp = provider.next(remaining());
		}
	}
	
	public void pushFront(Task t, Duration d) {
		ScheduledTask st = new ScheduledTask(t,ts.getStart(),d);
		nextAvailable = nextAvailable.plus(d);
		tasks.add(0,st);
	}
	
	public void pushFront(Task t) {
		pushFront(t,t.getDuration());
	}
	
	public Duration remaining() {
		return Duration.between(nextAvailable, ts.getStart().plus(ts.getDuration()));
	}
	
	public void pushBack(Task t, Duration d) {
		ScheduledTask st = new ScheduledTask(t,nextAvailable,d);
		nextAvailable = nextAvailable.plus(d);
		tasks.add(st);
	}
	
	public void pushBack(Task t) {
		pushBack(t,t.getDuration());
	}
	
	public void shift(Duration d) {
		for(ScheduledTask st: tasks) {
			st.shift(d);
		}
	}
	
	public void shiftTo(LocalDateTime time) {
		ScheduledTask task = currentTask();
		if(task == null) return;
		shift(Duration.between(task.getStart(), time));
	}
	
	public void shiftToStart() {
		ScheduledTask task = currentTask();
		if(task == null) return;
		shift(Duration.between(task.getStart(), ts.getStart()));
	}
	
	private ScheduledTask popFront() {
		ScheduledTask st = tasks.remove(0);
		shiftToStart();
		if(st != null) {
			nextAvailable = nextAvailable.minus(st.getRealizedDuration());
		}
		return st;
	}
	
	public void postpone() {
		ScheduledTask st = popFront();
		if(st == null) return;
		// even though pop front shifts to start, we want the next task to start now, not however long ago
		shiftTo(LocalDateTime.now());
		Task next = provider.next(remaining());
		pushFront(next);
	}
	
	public void extend(Duration d) {
		ScheduledTask st = popFront();
		if(st == null) return;
		// pop shifts everything back to the start of the time slot, and push front will shift forward accordingly
		pushFront(st.getTask(),st.getRealizedDuration().plus(d));
	}
	
	public void finish() {
		ScheduledTask st = popFront();
		if(st == null) return;
		shiftTo(LocalDateTime.now());
	}
	
	public List<ScheduledTask> getTasks(){
		return tasks;
	}
	
	public ScheduledTask currentTask() {
		return (tasks.size() == 0) ? null : tasks.get(0);
	}
}
