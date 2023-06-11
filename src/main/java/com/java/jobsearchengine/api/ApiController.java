package com.java.jobsearchengine.api;

import com.java.jobsearchengine.job.Job;
import com.java.jobsearchengine.job.JobController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/request")
public class ApiController {

    private final JobController jobController;
    private final ApiService apiService;

    @Autowired
    public ApiController(JobController jobController, ApiService apiService) {
        this.jobController = jobController;
        this.apiService = apiService;
    }

    @GetMapping("/") //Request
    public List<Job> getJobs(String jobTitle, String location){
        return apiService.getJobs(jobTitle,location);
    }


}
