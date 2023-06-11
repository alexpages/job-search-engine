package com.java.jobsearchengine.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    //TODO review method
    public List<Job> getJobs(String jobTitle, String location) throws InterruptedException {
        List<Job> listJobs = jobService.getJobs(jobTitle, location);
        if (listJobs == null){
            fetchData(jobTitle, location);
            listJobs = jobService.getJobs(jobTitle, location);
        }
        return listJobs;
    }

    public void fetchData(String jobTitle, String location) throws InterruptedException {
        jobService.fetchData(jobTitle, location);
    }

    public Job createJob(ArrayList<String> elements) {
        return jobService.createJob(elements);
    }

    @DeleteMapping("/delete")
    public void deleteJobs(@RequestBody Job job){
        jobService.deleteJobs(job);
    }

}
