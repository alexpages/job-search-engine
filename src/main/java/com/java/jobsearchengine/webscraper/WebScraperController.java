package com.java.jobsearchengine.webscraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebScraperController {

    private final WebScraperService webScraperService;

    @Autowired
    public WebScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }

    public List<List<String>> fetchNewData(String jobTitle, String location){
        return webScraperService.fetchNewData(jobTitle, location);
    }
}
