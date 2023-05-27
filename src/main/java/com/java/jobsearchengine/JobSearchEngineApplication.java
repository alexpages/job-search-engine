package com.java.jobsearchengine;

import com.java.jobsearchengine.job.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class JobSearchEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSearchEngineApplication.class, args);
	}
}
