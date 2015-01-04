package com.rabbit.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("jobg2 executing...");
	}

}
