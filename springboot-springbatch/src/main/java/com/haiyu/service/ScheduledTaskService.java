package com.haiyu.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2018/5/20.
 */
@Service
public class ScheduledTaskService {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job importJob;
    public JobParameters jobParameters;
    @Scheduled(fixedRate = 5000)
    public void execute()throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        String date =df.format(System.currentTimeMillis());
        jobParameters = new JobParametersBuilder()
                .addString("time",date)
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);
    }
}