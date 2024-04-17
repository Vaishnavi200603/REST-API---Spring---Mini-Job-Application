package com.example.firstJobApp.Job;

import com.example.firstJobApp.Company.Company;
import jakarta.persistence.*;

@Entity
//by default with entity annotation it takes the class name as table name only,
//like here it is job
//but if we want to provide any other name to the table we use table annotation,
//where we can provide the table name, like here it is job_table
//@Table(name = "job_table")
public class Job {
    @Id //this creates the variable primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //if we don't want to provide manual id - then this annotation automatically generate it
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne
    private Company company;

    //whenever we are working with JPA - we need to create default Constructor
    //BUT WHY - because entities are object that are represent the persistent data in the relational database
    //OR we can say JPA create instances of entity during the retrieval of data from the database,
    //and without this default constructor JPA won't be able to instantiate any entity object
    public Job() {
    }

    //with Default constructor we are free to have more constructor for our use only and not for JPA
    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
