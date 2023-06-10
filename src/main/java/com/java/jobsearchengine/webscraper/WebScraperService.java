package com.java.jobsearchengine.webscraper;

import com.java.jobsearchengine.nlp.NlpController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WebScraperService {

    @Autowired
    private final ChromeDriver driver;
    private final NlpController nlpController;

    @Autowired
    public WebScraperService(ChromeDriver driver, NlpController nlpController) {
        this.driver = driver;
        this.nlpController = nlpController;
    }

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
        WebElement jobSearchBoard = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("jobs-search__results-list")));
        List<WebElement> jobCards = jobSearchBoard
                .findElements(By.className("base-card"));
        //Obtain all links by pressing on every card and getting current url
        for (WebElement jobCard: jobCards){
            jobCard.findElement(By.tagName("a")).click();
            links.add(driver.getCurrentUrl());
        }
        return links;
    }

    public String obtainJobDescription(String URL){
        StringBuilder sb = new StringBuilder();
        //Go to webpage
        driver.get(URL);
        //Find and click 'Show More button'
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("/html/body/div[3]/div/section/div[2]/div/section[1]/div/div/section/button[1]")));
        button.click();
        //Find class of Job Description
        final WebElement jobDescription = driver.findElement(By
                .className("show-more-less-html__markup"));
        sb.append(jobDescription.getText());
        driver.close();
        return sb.toString();
    }

    public List<String> obtainJobInfo(WebElement webElement) {
        List<String> extractedInfo = new ArrayList<>();
        extractedInfo.add(webElement.findElement(By.className("top-card-layout__title")).getText());
        extractedInfo.add(webElement.findElement(By.className("topcard__flavor")).getText());
        extractedInfo.add(webElement.findElement(By.className("topcard__flavor--bullet")).getText());
        extractedInfo.add(webElement.findElement(By.className("posted-time-ago__text")).getText());
        return extractedInfo;
    }

    public List<List<String>> fetchNewData(String jobTitle, String location){
        List<List<String>>scrapedJobs = null;
        ArrayList<String> finalLinks = null;

        ArrayList<String> links = obtainJobUrls(jobTitle, location);
        for (String link : links){
            String description = obtainJobDescription(link);
            boolean result = nlpController.validateJob(description);
            if(nlpController.validateJob(obtainJobDescription(link))){
                finalLinks.add(link);
            }
        }
        for (String finalLink : finalLinks){
            driver.get(finalLink);
            WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("/html/body/div[3]/div/section/div[2]/section/div")));
            scrapedJobs.add(obtainJobInfo(webElement));
            driver.close();
        }
        driver.quit();
        return scrapedJobs;
    }
}
