package com.java.jobsearchengine.webscraper;

import com.java.jobsearchengine.nlp.NlpController;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WebScraperService {

    private final ChromeDriver driver;
    private final NlpController nlpController;

    @Autowired
    public WebScraperService(ChromeDriver driver, NlpController nlpController) {
        this.driver = driver;
        this.nlpController = nlpController;
    }

    public String obtainJobUrl (WebElement webElement) {
//        String link = webElement.findElement(By.cssSelector("a.base-card__full-link")).getAttribute("href");
        String link = webElement.findElement(By
                .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div"))
                .findElement(By.xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/a")).getAttribute("href");

        return link;
    }

    public String obtainJobDescription(WebElement webElement){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("show-more-less-html__button"))).click();

        String description = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("description__text"))).getText();

//        String description = webElement.findElement(By.className("show-more-less-html__markup")).getText();
        ///html/body/div[3]/div/section/div[2]/div/section[1]/div/div/section/div
        ///html/body/div[3]/div/section/div[2]/div/section[1]/div/div

        //show-more-less-html__markup
//        show-more-less-html__markup

        return description;
    }

    public List<String> obtainJobInfo(WebElement webElement) {
        List<String> extractedInfo = new ArrayList<>();
        extractedInfo.add(new WebDriverWait(driver, Duration.ofSeconds(2)) //Job title
                .until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/div[2]/h3"))).getText());
        extractedInfo.add(new WebDriverWait(driver, Duration.ofSeconds(2)) //Company
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/div[2]/h4"))).getText());
        extractedInfo.add(new WebDriverWait(driver, Duration.ofSeconds(2)) //Location
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/div[2]/div/span"))).getText());
        extractedInfo.add(new WebDriverWait(driver, Duration.ofSeconds(2)) //Time
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/div[2]/div/time"))).getText());
        return extractedInfo;
    }

    public List<List<String>> fetchNewData(String jobTitle, String location){
        //Get first 25 job cards
        String mainURL = "https://www.linkedin.com/jobs/search?" +
                "keywords=" + jobTitle +
                "&location=" + location +
                "&geoId=&trk=public_jobs_jobs-search-bar_search-submit&" +
                "position=" + "1" +
                "&pageNum=0";
        driver.get(mainURL);
        WebElement jobSearchBoard = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id=\"main-content\"]/section[2]/ul")));
        List<WebElement> jobCards = jobSearchBoard
                .findElements(By.className("base-card__full-link"));

        //Iterate over every job card
        List<List<String>>scrapedJobs = new ArrayList<>();
        for (WebElement jobCard: jobCards) {
            jobCard.click();
            scrollDown(driver);
            String jobDescription = obtainJobDescription(jobCard);
            //Is a valid job
            boolean verification = nlpController.validateJob(jobDescription);
            if (verification) {
                List<String> info = obtainJobInfo(jobCard);
                info.add(obtainJobUrl(jobCard));
                scrapedJobs.add(info);
            }
        }
        driver.quit();
        return scrapedJobs;
    }

    private static void scrollDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollIncrement = 17;
        int totalScroll = scrollIncrement * 10;
        for (int i = 0; i < totalScroll; i += scrollIncrement) {
            jsExecutor.executeScript("window.scrollBy(0, " + scrollIncrement + ");");
        }
    }

}
