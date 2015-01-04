package com.rabbit.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job1 implements Job{
	private static final int DEFAULT_RETRY_TIME = 3;
	int count = 0;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer result = (Integer)context.get("result");
		if(result == null || result < DEFAULT_RETRY_TIME) {
			context.put("result", result == null?2:result + 1);
		}
		count++;
		System.out.println(count);
		JobExecutionException j = new JobExecutionException();
		j.setRefireImmediately(true);
		throw j;
	}
}
