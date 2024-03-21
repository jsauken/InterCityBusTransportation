package kz.iitu.intercitybustransportation.mapper;


import kz.iitu.intercitybustransportation.dto.ReviewDTO;
import kz.iitu.intercitybustransportation.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ReviewMapper {

    private final UserMapper userMapper;
    private final FlightMapper flightMapper;

    @Autowired
    public ReviewMapper(UserMapper userMapper, FlightMapper flightMapper) {
        this.userMapper = userMapper;
        this.flightMapper = flightMapper;
    }

    public ReviewDTO toDto(Review review) {
        ReviewDTO reviewDto = new ReviewDTO();
        reviewDto.setId(review.getId());
        reviewDto.setUser(userMapper.toDto(review.getUser()));
        reviewDto.setFlight(flightMapper.toDto(review.getFlight()));
        reviewDto.setText(review.getText());
        reviewDto.setRating(review.getRating());
        return reviewDto;
    }

    public Review toEntity(ReviewDTO reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setUser(userMapper.toEntity(reviewDto.getUser()));
        review.setFlight(flightMapper.toEntity(reviewDto.getFlight()));
        review.setText(reviewDto.getText());
        review.setRating(reviewDto.getRating());
        return review;
    }
}
