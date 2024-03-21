package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
}