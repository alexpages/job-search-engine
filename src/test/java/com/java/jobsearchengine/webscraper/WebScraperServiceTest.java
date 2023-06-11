package com.java.jobsearchengine.webscraper;

import org.openqa.selenium.JavascriptExecutor;
import com.java.jobsearchengine.nlp.NlpController;
import com.java.jobsearchengine.nlp.NlpService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class WebScraperServiceTest {

    @Mock private NlpService nlpService;
    @InjectMocks private NlpController nlpController;
    private ChromeDriver driver = new ChromeDriver();
    private WebScraperService underTest;

    @BeforeEach
    void setUp() {
        underTest = new WebScraperService(driver, nlpController);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void obtainJobUrls() {
        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Germany&trk=public_jobs_jobs-search-bar_search-submit&position=2&pageNum=0&currentJobId=3625258171";
        driver.get(URL);
        WebElement jobCard = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .className("base-card")));
        //when
        String result = underTest.obtainJobUrl(jobCard);
        //then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(2)
    void obtainJobDescription() {
        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Germany&trk=public_jobs_jobs-search-bar_search-submit&position=3&pageNum=0";
        driver.get(URL);
        WebElement jobCard = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//a[@data-tracking-control-name='public_jobs_jserp-result_search-card']")));
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            System.out.println("Time");
        }
        //when
        String result = underTest.obtainJobDescription(jobCard);
        //then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(3)
    void obtainJobInfo() {
        //given
        String URL = "https://www.linkedin.com/jobs/search?keywords=Junior%20Java&location=Germany&trk=public_jobs_jobs-search-bar_search-submit&position=2&pageNum=0&currentJobId=3625258171";
        driver.get(URL);
        WebElement jobCard = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//a[@data-tracking-control-name='public_jobs_jserp-result_search-card']")));
        //when
        List<String> result = underTest.obtainJobInfo(jobCard);
        //then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(4)
    void fetchNewData() throws InterruptedException {
        //given
        String jobTitle = "Junior Java";
        String location = "Spain";
        //when
        List<List<String>> result = underTest.fetchNewData(jobTitle, location);
        //then
        assertThat(result).isNotNull();
    }
}