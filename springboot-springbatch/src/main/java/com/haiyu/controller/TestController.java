package com.haiyu.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2018/5/20.
 */
@RestController
public class TestController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job importJob;
    public JobParameters jobParameters;

    @RequestMapping("/imp")
    public String imp ()throws Exception{
        String fileName = "person";
        String path = fileName+".csv";
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        String date =df.format(System.currentTimeMillis());
        jobParameters = new JobParametersBuilder()
                .addString("time",date)
                .addString("input.file.name",path)
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);
        return "ok";
    }

}
