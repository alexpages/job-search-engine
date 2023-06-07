package com.java.jobsearchengine.job;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JobRepository extends MongoRepository<Job, String> {

    public long count();
}
