package com.java.jobsearchengine.nlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
