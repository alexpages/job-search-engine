package com.java.jobsearchengine.webscraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String scrapWebsite(String URL)
    {
        StringBuilder sb = new StringBuilder();
        driver.get(URL);
        final WebElement sentences = driver.
                findElement(By.cssSelector("show-more-less-html__markup relative overflow-hidden"));

        //Loged in class -> "jobs-box__html-content jobs-description-content__text t-14 t-normal jobs-description-content__text--stretch"
        final List<WebElement> pElementsJobDescription = sentences.findElements(By.tagName("p"));
        for (WebElement sentence : pElementsJobDescription){
            sb.append(sentence.getText());
        }
        driver.quit();
        return sb.toString();
    }
}
