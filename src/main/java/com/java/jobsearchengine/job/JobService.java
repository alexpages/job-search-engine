package com.java.jobsearchengine.job;

import com.java.jobsearchengine.nlp.NlpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final NlpService nlpService;

    @Autowired
    public JobService(JobRepository jobRepository, NlpService nlpService) {
        this.jobRepository = jobRepository;
        this.nlpService = nlpService;
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    public void postJobs(Job job) {
        if (nlpService.scoreKeyWords(job.getDescription())){
            jobRepository.insert(job);
        }
    }

    public void deleteJobs(Job job) {
        jobRepository.delete(job);
    }
}
