package com.example.paymentgateway.service;

import com.example.paymentgateway.entity.Transaction;
import com.example.paymentgateway.entity.User;
import com.example.paymentgateway.repository.TransactionRepository;
import com.example.paymentgateway.util.LuhnValidator;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;

    public PaymentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction makePayment(String cardNumber, Double amount, User user) {

        Transaction txn = new Transaction();
        txn.setAmount(amount);
        txn.setTransactionType("PAYMENT");
        txn.setUser(user);

        if (!LuhnValidator.isValid(cardNumber)) {
            txn.setStatus("FAILED");
        } else {
            String[] statuses = {"SUCCESS", "FAILED", "PENDING"};
            txn.setStatus(statuses[new Random().nextInt(3)]);
        }

        return transactionRepository.save(txn);
    }
}
