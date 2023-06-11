package com.java.jobsearchengine.webscraper;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebScraperConfig {

    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webScraperDriver/chromedriver");
    }

    @Bean
    public ChromeDriver driver(){
        return new ChromeDriver();
    }
}
