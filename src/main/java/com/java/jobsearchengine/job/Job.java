package com.java.jobsearchengine.job;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("job")
public class Job {

    @Id
    private Long id;
    private String title;
    private String company;
    private String link;
    public Job() {
    }

    public Job(Long id, String title, String company) {
        this.id = id;
        this.title = title;
        this.company = company;
    }

    public Job(Long id, String title, String company, String link) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLink() {
        return link;
    }

    public void setYoe(String link) {
        this.link = link;
    }
}
