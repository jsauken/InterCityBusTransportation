package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO getReview(Long id);
    List<ReviewDTO> getAllReviews();
    ReviewDTO createReview(ReviewDTO reviewDto);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDto);
    void deleteReview(Long id);
}
