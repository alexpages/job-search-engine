package com.java.jobsearchengine.nlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public boolean validateJob(String jobDescription){
        boolean output = nlpService.scoreKeyWords(jobDescription);
        if (output) System.out.println("YESSSS");
        else System.out.println("NOOOOO");
        return output;
    }
}
