package com.josephsullivan256.gmail.schedule;

import java.time.Duration;
import java.time.LocalDateTime;

// IDEA : Instead of making a ui, just use a bunch of files, where the program runs in the base folder
// each task could be a file
// there could be files specifying how to generate files for tasks
// the actual program will call the generators, get all the tasks stored up

public class Main {
	
	public static void main(String[] args) {
		Scheduler sr = new Scheduler();
		
		sr.addTask(make(1,1));
		sr.addTask(make(1,2));
		sr.addTask(make(2,1));
		sr.addTask(make(3,1));
		sr.addTask(make(4,1));
		
		TimeSlot ts = new TimeSlot(LocalDateTime.now().plus(Duration.ofHours(1)), Duration.ofHours(5));
		
		Schedule s = sr.schedule(ts,Scheduler.dumbSelector);
		
		ScheduledTask current = s.currentTask();
		while(current != null) {
			System.out.println(current.getTask());
			System.out.println();
			
			s.finish();
			current = s.currentTask();
		}
	}
	
	public static Task make(int daysLater, int hour) {
		String description = "filler";
		LocalDateTime due = LocalDateTime.now().plus(Duration.ofDays(daysLater));
		Duration duration = Duration.ofHours(hour);
		
		return new Task(description, due, duration);
	}
}
