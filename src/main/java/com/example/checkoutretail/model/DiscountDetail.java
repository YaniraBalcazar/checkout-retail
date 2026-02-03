package com.example.checkoutretail.model;

import java.math.BigDecimal;

public class DiscountDetail {
    private String description;
    private BigDecimal amount;

    public DiscountDetail(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
