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
                    "Backend Developer",
                    "Meta",
                    "QUALIFICATION REQUIREMENT\n" +
                            "\n" +
                            "Minimum 1 Year experience as a Backend engineer\n" +
                            "Bachelor's Degree with Computer Science or equivalent\n" +
                            "Managing multiple tasks and timelines in a fast-paced environment\n" +
                            "Eager to learn and A strong team player\n",
                    "meta@gmail.com",
                    "NA"
            );
            Job job2 = new Job(
                    "Java Backend Developer",
                    "Microsoft",
                    "QUALIFICATION REQUIREMENT\n" +
                            "\n" +
                            "Minimum 3 Years experience as a Backend engineer\n" +
                            "Bachelor's Degree with Computer Science or equivalent\n" +
                            "Managing multiple tasks and timelines in a fast-paced environment\n" +
                            "Eager to learn and A strong team player\n",
                    "microsoft@gmail.com",
                    "NA"
            );
            jobRepository.insert(List.of(job1, job2));
        };
    }
}
