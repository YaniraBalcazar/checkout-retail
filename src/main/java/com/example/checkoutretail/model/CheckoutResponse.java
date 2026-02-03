package com.example.checkoutretail.model;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutResponse {
    private BigDecimal subtotal;
    private List<DiscountDetail> discounts;
    private BigDecimal total;

    public CheckoutResponse(BigDecimal subtotal, List<DiscountDetail> discounts, BigDecimal total) {
        this.subtotal = subtotal;
        this.discounts = discounts;
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public List<DiscountDetail> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountDetail> discounts) {
        this.discounts = discounts;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
