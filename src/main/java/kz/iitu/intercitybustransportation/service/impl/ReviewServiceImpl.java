package kz.iitu.intercitybustransportation.service.impl;

import kz.iitu.intercitybustransportation.dto.ReviewDTO;
import kz.iitu.intercitybustransportation.exceptions.ResourceNotFoundException;
import kz.iitu.intercitybustransportation.mapper.ReviewMapper;
import kz.iitu.intercitybustransportation.model.Review;
import kz.iitu.intercitybustransportation.repository.ReviewRepository;
import kz.iitu.intercitybustransportation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDTO getReview(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDto) {
        return reviewRepository.findById(id)
                .map(review -> {
                    // Update the fields of the review as per your requirements
                    Review updatedReview = reviewRepository.save(review);
                    return reviewMapper.toDto(updatedReview);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.findById(id)
                .ifPresentOrElse(reviewRepository::delete, () -> {
                    throw new ResourceNotFoundException("Review not found with id " + id);
                });
    }
}
