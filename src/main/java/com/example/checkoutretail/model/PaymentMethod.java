package com.example.checkoutretail.model;

public enum PaymentMethod {
    DEBIT,
    CREDIT,
    CASH;

    public static PaymentMethod fromString(String s) {
        if (s == null) return null;
        return PaymentMethod.valueOf(s.toUpperCase());
    }
}