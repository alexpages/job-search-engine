package com.java.jobsearchengine.job;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("job")
public class Job {
    @Id
    private String id; //to be updated
    private String title;
    private String company;
    private String contact;
    private String location;
    private String link;

    public Job() {
    }

    public Job(String title,  String location, String company, String contact, String link) {
        this.title = title;
        this.location = location;
        this.company = company;
        this.contact = contact;
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
