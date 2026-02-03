package com.example.checkoutretail.controller;

import com.example.checkoutretail.model.Cart;
import com.example.checkoutretail.model.CheckoutResponse;
import com.example.checkoutretail.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody Cart cart) {
        CheckoutResponse response = checkoutService.checkout(cart);
        return ResponseEntity.ok(response);
    }
}
