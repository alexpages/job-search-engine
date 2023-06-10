package com.java.jobsearchengine.api;

import com.java.jobsearchengine.job.JobController;
import com.java.jobsearchengine.nlp.NlpController;
import com.java.jobsearchengine.webscraper.WebScraperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ApiService {

    private final NlpController nlpController;
    private final JobController jobController;
    private final WebScraperController webScraperController;

    @Autowired
    public ApiService(NlpController nlpController, JobController jobController, WebScraperController webScraperController) {
        this.nlpController = nlpController;
        this.jobController = jobController;
        this.webScraperController = webScraperController;
    }

    public boolean validateLocation(String location){
        Locale[] availableLocales = Locale.getAvailableLocales();

        for (Locale locale : availableLocales) {
            if (location.equalsIgnoreCase(locale.getDisplayCountry())) {
                return true;
            }
        }
        return false;

    }

}
