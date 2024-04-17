package com.example.firstJobApp.Job;

import com.example.firstJobApp.Company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    //Spring Boot will make sure to JobService object(jobService) available
    // to this controller, and it will get initialized as @service Annotation
    // is provided to JobServiceImpl Class (This is quite a loosed coupled) -
    //Basically we just refactor our code to add a service
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //endpoint where we are returning all the jobs
    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
        //another way - return ResponseEntity.ok(jobService.findAll));
    }

    @PostMapping
    public ResponseEntity<String> createJobs(@RequestBody Job job){
        jobService.createJob(job);
//        Company c = job.getCompany(); it will create the company if not exits,
        //but we don't use this as we want to create company first and then job
        //and with this statement we have to provide full detail like company id, name
        //description but if we create company first we just link the job with company id
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.OK);
    }

    //{} -> indicate dynamic
    //{id} -> id -> variable name
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobs(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted) return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
//    @RequestMapping(value = "jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated = jobService.updateJobById(id, updatedJob);
        if(updated) return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
