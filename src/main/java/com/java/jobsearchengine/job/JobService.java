package com.java.jobsearchengine.job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    public List<Job> getJobs() {
        return List.of(
                new Job(1L,"Backend Developer","Microsoft"));
    }


}
