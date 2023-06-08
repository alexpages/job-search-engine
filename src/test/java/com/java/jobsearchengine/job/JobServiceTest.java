package com.java.jobsearchengine.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JobServiceTest {

    private final JobService underTest;

    @Autowired
    JobServiceTest(JobService underTest) {
        this.underTest = underTest;
    }

    @Test
    void getJobs() {
    }

    @Test
    void postJobs() {
    }

    @Test
    void deleteJobs() {
    }
}