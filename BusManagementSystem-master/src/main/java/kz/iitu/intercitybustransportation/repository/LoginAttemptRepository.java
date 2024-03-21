package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

 @Query(value = "SELECT * FROM login_attempt WHERE email = :email ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
 List<LoginAttempt> findRecent(String email);
}
