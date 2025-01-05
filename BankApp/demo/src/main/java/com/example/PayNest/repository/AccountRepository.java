package com.example.PayNest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PayNest.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}

