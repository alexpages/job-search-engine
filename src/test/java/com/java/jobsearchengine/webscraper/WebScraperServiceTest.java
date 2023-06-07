package com.java.jobsearchengine.webscraper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class WebScraperServiceTest {

    private WebScraperService underTest;

    @BeforeEach
    void setUp() {
        ChromeDriver driver = new ChromeDriver();
        underTest = new WebScraperService(driver);
    }

    @Test
    void scrapWebsite() {

        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Alemania&locationId=&geoId=101282230&f_TPR=&f_E=2%2C3&f_JT=F&position=1&pageNum=0";

        //when
        String result = underTest.scrapWebsite(URL);

        //then
        assertThat(result).hasToString("hola");



    }
}