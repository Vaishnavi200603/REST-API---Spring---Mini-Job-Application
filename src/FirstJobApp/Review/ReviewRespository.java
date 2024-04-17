package com.example.firstJobApp.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //this is custom method calling from ReviewServiceImpl - we don't need to provide
    //method body for this, as JPA do that by their own as break down the method name -
    //findByCompanyId == findBy , CompanyId
    //findBy is the predefined method in JPA Repository
    //CompanyId is an instance created in Review Class
    List<Review> findByCompanyId(Long companyId);
}
