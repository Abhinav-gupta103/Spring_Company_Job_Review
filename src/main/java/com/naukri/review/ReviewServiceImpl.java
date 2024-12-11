package com.naukri.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naukri.company.Company;
import com.naukri.company.CompanyService;

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
        List<Review> reviews = this.reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean createReview(Long companyId, Review review) {
        Company company = this.companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            this.reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = this.getAllReviews(companyId);
        return reviews.stream()
                .filter((review) -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Review review = this.getReview(companyId, reviewId);
        if (review != null) {
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            this.reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public Boolean deletedReview(Long companyId, Long reviewId) {
        Review review = this.getReview(companyId, reviewId);
        if (review != null) {
            this.reviewRepository.deleteById(reviewId);
            return true;
        } else
            return false;
    }

}
