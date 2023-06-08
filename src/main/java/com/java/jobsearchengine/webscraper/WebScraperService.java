package com.java.jobsearchengine.webscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperService {

    private String URL = "https://www.linkedin.com/jobs/collections/recommended/?currentJobId=3629866884";

    @Autowired
    private final ChromeDriver driver;

    @Autowired
    public WebScraperService(ChromeDriver driver) {
        this.driver = driver;
    }

//    public ArrayList<String> obtainMetadata(WebElement baseCard){
//        ArrayList<String> metadata = new ArrayList<>();
//
//    }


    public ArrayList<String> obtainJobUrls (String jobTitle, String location) {
        ArrayList<String> links = new ArrayList<>();
        //Create main URL
        String mainURL = "https://www.linkedin.com/jobs/search?" +
                "keywords=" + jobTitle +
                "&location=" + location +
                "&geoId=&trk=public_jobs_jobs-search-bar_search-submit&" +
                "position=" + "1" +
                "&pageNum=0";
        driver.get(mainURL);
        //Obtain entire job board div and get all 'li' elements (job cards)
        WebElement jobSearchBoard = driver.findElement(By.className("jobs-search__results-list"));

        List<WebElement> jobCards = jobSearchBoard
                .findElements(By.className("base-card"));
        //Obtain all links by pressing on every card and getting current url
        for (WebElement jobCard: jobCards){
            jobCard.findElement(By.tagName("a")).click();
            links.add(driver.getCurrentUrl());
        }
        driver.quit();
        return links;
    }

    public String obtainJobDescription(String URL)
    {
        StringBuilder sb = new StringBuilder();
        //Go to webpage
        driver.get(URL);
        //Find and click 'Show More button'
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("/html/body/div[3]/div/section/div[2]/div/section[1]/div/div/section/button[1]")));
        button.click();
        //Find class of Job Description
        final WebElement jobDescription = driver.findElement(By.
                xpath("/html/body/div[3]/div/section/div[2]/div/section[1]/div/div/section/div"));
        sb.append(jobDescription.getText());
        driver.quit();
        return sb.toString();
    }
}
