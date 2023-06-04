package com.java.jobsearchengine.job;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {

//    default List<Job> findAll(){
//        return this.findAll();
//    }

    public long count();
}
