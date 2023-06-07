package com.java.jobsearchengine.job;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("job")
public class Job {
    @Id
    private String id; //to be updated
    private String title;
    private String company;
    private String description;
    private String contact;
    private String link;

    public Job() {
    }

    public Job(String title, String company, String contact) {
        this.title = title;
        this.company = company;
        this.contact = contact;
    }

    public Job(String title, String company, String description, String contact, String link) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.contact = contact;
        this.link = link;
    }

    public String getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
