package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}