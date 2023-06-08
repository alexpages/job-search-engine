package com.java.jobsearchengine.api;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {



    //Fetch jobs -> Scheduled every 10 minutes

    //Get jobs -> job title, location
    //  No jobs -> call fetching calling WebScrappingController, then call JobController to show
    //  Jobs -> get jobs calling JobController

}
