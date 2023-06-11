package com.java.jobsearchengine.webscraper;

import com.java.jobsearchengine.nlp.NlpController;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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
        String link = webElement.findElement(By
                .xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div"))
                .findElement(By.xpath("//*[@id=\"main-content\"]/section[2]/ul/li[1]/div/a")).getAttribute("href");

        return link;
    }

    public String obtainJobDescription(WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement.findElement(By.xpath("//button[@data-tracking-control-name ='public_jobs_show-more-html-btn']")));
        String description = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("show-more-less-html__markup"))).getText();
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

    public List<List<String>> fetchNewData(String jobTitle, String location) throws InterruptedException {
        //Get first 25 job cards
        String mainURL = "https://www.linkedin.com/jobs/search?" +
                "keywords=" + jobTitle +
                "&location=" + location +
                "&geoId=&trk=public_jobs_jobs-search-bar_search-submit&" +
                "position=" + "1" +
                "&pageNum=0";
        driver.get(mainURL);
        TimeUnit.MILLISECONDS.sleep(100);
        zoomOut(driver);
        WebElement jobSearchBoard = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("jobs-search__results-list")));
        List<WebElement> jobCards = jobSearchBoard
                .findElements(By.xpath("//a[@data-tracking-control-name='public_jobs_jserp-result_search-card']"));

        JavascriptExecutor executor = driver;
        List<List<String>>scrapedJobs = new ArrayList<>();
        for (WebElement jobCard: jobCards) {
            executor.executeScript("arguments[0].click();", jobCard);
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

    //JavaScript scripts
    private static void scrollDown(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int scrollIncrement = 15;
        int totalScroll = scrollIncrement * 10;
        for (int i = 0; i < totalScroll; i += scrollIncrement) {
            jsExecutor.executeScript("window.scrollBy(0, " + scrollIncrement + ");");
        }
        //TODO
        try {
            TimeUnit.MILLISECONDS.sleep(150);
        } catch (Exception e) {
            System.out.println("Time");
        }
    }

    private static void zoomOut(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String zoomChrome = "document.body.style.zoom = '25.0%'";
        js.executeScript(zoomChrome);
    }

}
