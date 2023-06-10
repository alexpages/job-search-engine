package com.java.jobsearchengine.webscraper;

import com.java.jobsearchengine.nlp.NlpController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class WebScraperServiceTest {

    private NlpController nlpController;
    private WebScraperService underTest;
    ChromeDriver driver = new ChromeDriver();

    @BeforeEach
    void setUp() {
        underTest = new WebScraperService(driver, nlpController);
    }
    @AfterEach
    void finish(){
        driver.quit();
    }

    @Test
    @Order(1)
    void obtainJobUrls() {
        //given
        String location = "Germany";
        String jobTitle = "Junior Java";
        //when
        ArrayList<String> result = underTest.obtainJobUrls(jobTitle,location);
        //then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(2)
    void obtainJobDescription() {
        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Germany&trk=public_jobs_jobs-search-bar_search-submit&position=2&pageNum=0&currentJobId=3625258171";
        //when
        String result = underTest.obtainJobDescription(URL);
        //then
    }

    @Test
    @Order(3)
    void obtainJobInfo() {
        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Germany&trk=public_jobs_jobs-search-bar_search-submit&position=2&pageNum=0&currentJobId=3625258171";
        driver.get(URL);
        WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("top-card-layout__card")));
        //when
        List<String> result = underTest.obtainJobInfo(webElement);
        //then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(4)
    void fetchNewData() {
        //given
        String jobTitle = "Junior Java";
        String location = "Germany";
        //when
        List<List<String>> result = underTest.fetchNewData(jobTitle, location);
        //then
        assertThat(result).isNotNull();
    }
}