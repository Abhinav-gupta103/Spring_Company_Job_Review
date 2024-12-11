package com.naukri.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Boolean createReview(Long companyId, Review review);

    Review getReview(Long companyId, Long reviewId);

    Boolean updateReview(Long companyId, Long reviewId, Review updatedReview);

    Boolean deletedReview(Long companyId, Long reviewId);
}
