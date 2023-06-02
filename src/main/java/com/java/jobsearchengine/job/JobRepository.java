package com.java.jobsearchengine.job;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {

//    @Query("{title:'?0'}")
//    Job findItemByName(String name);
//
//    @Query(value="{company:'?0'}", fields="{'title' : 1, 'quantity' : 1}")
//    List<Job> findAll(String category);

    public default List<Job> findAll(){
        return List.of(
                new Job(1L,"Backend Developer","Microsoft"));
    }

    public long count();
}
