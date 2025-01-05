package com.example.PayNest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PayNest.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
