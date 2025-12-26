package com.example.paymentgateway.controller;

import com.example.paymentgateway.entity.Transaction;
import com.example.paymentgateway.entity.User;
import com.example.paymentgateway.repository.TransactionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Transaction> history(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return repository.findByUser(user);
    }
}
