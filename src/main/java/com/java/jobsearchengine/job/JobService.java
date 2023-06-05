package com.java.jobsearchengine.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    public void postJobs(Job job) {
        jobRepository.insert(job);
        System.out.println("'"+job.getTitle()+"'" + " Has been added");
    }

    public void deleteJobs(Job job) {
        jobRepository.delete(job);
    }
}
