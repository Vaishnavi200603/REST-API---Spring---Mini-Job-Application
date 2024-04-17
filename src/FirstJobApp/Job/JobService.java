package com.example.firstJobApp.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long Id);

    boolean deleteJobById(Long Id);
    boolean updateJobById(Long Id, Job updatedJob);
}
