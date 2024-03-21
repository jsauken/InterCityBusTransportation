package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}