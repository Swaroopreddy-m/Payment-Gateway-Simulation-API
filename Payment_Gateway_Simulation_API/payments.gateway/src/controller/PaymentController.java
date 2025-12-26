package com.example.paymentgateway.controller;

import com.example.paymentgateway.entity.Transaction;
import com.example.paymentgateway.entity.User;
import com.example.paymentgateway.service.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Transaction pay(@RequestParam String cardNumber,
                           @RequestParam Double amount,
                           Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return paymentService.makePayment(cardNumber, amount, user);
    }
}
