package com.example.firstJobApp.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{ //<class name, primary key DT>
    //Spring Data Repository works at the runtime

    //with the help of JobRepository Interface, we create Job Class Persistence
}
