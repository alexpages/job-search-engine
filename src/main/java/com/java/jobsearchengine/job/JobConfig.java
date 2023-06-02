package com.java.jobsearchengine.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobConfig {

    @Bean
    CommandLineRunner commandLineRunner(JobRepository jobRepository){
        return args -> {
            Job job1 = new Job(
                    1L,
                    "Backend Developer",
                    "Microsoft"
            );
            Job job2 = new Job(
                    1L,
                    "Backend Developer",
                    "Meta"
            );
            jobRepository.saveAll(List.of(job1, job2));
        };
    }
}
