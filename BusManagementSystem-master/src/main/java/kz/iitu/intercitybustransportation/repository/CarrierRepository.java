package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    @Override
    Carrier getById(Long aLong);


}
