package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
