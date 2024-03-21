package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
