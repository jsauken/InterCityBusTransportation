package kz.iitu.intercitybustransportation.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import kz.iitu.intercitybustransportation.model.LoginAttempt;
import kz.iitu.intercitybustransportation.repository.LoginAttemptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LoginService {

    private final LoginAttemptRepository repository;

    public LoginService(LoginAttemptRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addLoginAttempt(String email, boolean success) {

        LoginAttempt loginAttempt = new LoginAttempt();

        //email, success, LocalDateTime.now()
        loginAttempt.setEmail(email);
        loginAttempt.setSuccess(success);
        loginAttempt.setCreatedAt(LocalDateTime.now());
        repository.save(loginAttempt);
    }

    public List<LoginAttempt> findRecentLoginAttempts(String email) {
        return repository.findRecent(email);
    }
}