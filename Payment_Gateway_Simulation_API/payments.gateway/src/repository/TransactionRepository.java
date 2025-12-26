package com.example.paymentgateway.repository;

import com.example.paymentgateway.entity.Transaction;
import com.example.paymentgateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
