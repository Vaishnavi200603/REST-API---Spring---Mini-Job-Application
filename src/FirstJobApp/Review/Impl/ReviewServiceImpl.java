package com.example.firstJobApp.Review.Impl;

import com.example.firstJobApp.Company.Company;
import com.example.firstJobApp.Company.CompanyService;
import com.example.firstJobApp.Review.Review;
import com.example.firstJobApp.Review.ReviewRepository;
import com.example.firstJobApp.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        //this findByCompanyId() method is not present in JPA Repository -
        // so we create a custom method in ReviewRepository Interface
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyInfoById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review1 -> review1.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyInfoById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyInfoById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyInfoById(companyId) != null
                && reviewRepository.existsById(reviewId)){
            //1. Retrieving the review
            Review review = reviewRepository.findById(reviewId).orElse(null);
            //2. Retrieving the company
            Company company = review.getCompany();
            //3. Removing the review from company reference - because we did a bi-directional mapping
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyInfo(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
