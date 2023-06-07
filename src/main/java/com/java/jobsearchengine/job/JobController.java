package com.java.jobsearchengine.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/job")
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public List<Job> getJobs(){
        return jobService.getJobs();
    }

    @PostMapping("/post")
    public void postJobs(@RequestBody Job job){
        jobService.postJobs(job);
    }

    @DeleteMapping("/delete")
    public void deleteJobs(@RequestBody Job job){
        jobService.deleteJobs(job);
    }

}
