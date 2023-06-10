package com.java.jobsearchengine.nlp;

import com.java.jobsearchengine.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/nlp")
public class NlpController {

    private final NlpService nlpService;

    @Autowired
    public NlpController(NlpService nlpService) {
        this.nlpService = nlpService;
    }

    public boolean validateJob(String jobDescription){
        return nlpService.validateJob(jobDescription);
    }

}
