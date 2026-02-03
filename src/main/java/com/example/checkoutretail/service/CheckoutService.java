package com.example.checkoutretail.service;

import com.example.checkoutretail.model.*;
import com.example.checkoutretail.promotion.Promotion;
import com.example.checkoutretail.promotion.ProductPercentagePromotion;
import com.example.checkoutretail.promotion.BuyGetFreePromotion;
import com.example.checkoutretail.repository.Catalog;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    private final List<Promotion> promotions = Arrays.asList(
            new ProductPercentagePromotion("p-010", new BigDecimal("0.10")), // 10% en p-010
            new ProductPercentagePromotion("p-003", new BigDecimal("0.20")), // 20% en p-003
            new BuyGetFreePromotion("p-001", 2, 1)                         // compra 2 y lleva 1 gratis en p-001
    );

    public CheckoutResponse checkout(Cart cart) {
        // 1️⃣ Calcular subtotal
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            Optional<Product> pOpt = Catalog.findBySku(item.getSku());
            if (!pOpt.isPresent()) {
                throw new IllegalArgumentException("SKU desconocido: " + item.getSku());
            }
            BigDecimal price = pOpt.get().getPrice();
            subtotal = subtotal.add(price.multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // 2️⃣ Aplicar promociones
        List<DiscountDetail> discounts = new ArrayList<>();
        for (Promotion promo : promotions) {
            discounts.addAll(promo.apply(cart));
        }

        // 3️⃣ Sumar total de descuentos por promociones
        BigDecimal promoDiscountTotal = discounts.stream()
                .map(DiscountDetail::getAmount)
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        // 4️⃣ Descuento por medio de pago (por ejemplo, débito = 10%)
        BigDecimal paymentDiscount = BigDecimal.ZERO;
        PaymentMethod pm = PaymentMethod.fromString(cart.getPaymentMethod());
        if (pm == PaymentMethod.DEBIT) {
            BigDecimal base = subtotal.subtract(promoDiscountTotal);
            paymentDiscount = base.multiply(new BigDecimal("0.10")).setScale(0, BigDecimal.ROUND_HALF_UP);
            discounts.add(new DiscountDetail("Descuento por medio de pago: DÉBITO 10%", paymentDiscount));
        }

        // 5️⃣ Calcular total final
        BigDecimal total = subtotal.subtract(promoDiscountTotal).subtract(paymentDiscount);
        if (total.compareTo(BigDecimal.ZERO) < 0) total = BigDecimal.ZERO;

        return new CheckoutResponse(subtotal, discounts, total);
    }
}
