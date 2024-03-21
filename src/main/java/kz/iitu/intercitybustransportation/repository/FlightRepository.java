package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Flight;
import kz.iitu.intercitybustransportation.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getByArrivalTime(Date arrivalTime);
    Flight getById(Long id);

    Page<Flight> findByArrivalTime(Date arrivalTime, Pageable pageable);
    Page<Flight> findByDepartureTime(Date departureTime, Pageable pageable);
    Page<Flight> findByRoute(Route route, Pageable pageable);

    @Query(value = "select f from Flight f JOIN f.route r where r.origin = :origin and r.destination = :destination")
    Page<Flight> findAllByRoute(@Param("origin") String origin, @Param("destination") String destination, Pageable pageable);



}