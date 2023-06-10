package com.java.jobsearchengine.job;

import com.java.jobsearchengine.nlp.NlpController;
import com.java.jobsearchengine.webscraper.WebScraperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final NlpController nlpController;
    private final WebScraperController webScraperController;

    @Autowired
    public JobService(JobRepository jobRepository, NlpController nlpController, WebScraperController webScraperController) {
        this.jobRepository = jobRepository;
        this.nlpController = nlpController;
        this.webScraperController = webScraperController;
    }

    public List<Job> getJobs(String jobTitle, String location) {
        return jobRepository.findAll();
    }

    public void fetchData(String jobTitle, String location) {

        //TODO
    }

    public void deleteJobs(Job job) {
        jobRepository.delete(job);
    }

    public Job createJob(ArrayList<String> elements) {
        Job job = new Job(
                elements.get(0),
                elements.get(1),
                elements.get(2),
                elements.get(3),
                elements.get(4)
        );
        return job;
    }
}
