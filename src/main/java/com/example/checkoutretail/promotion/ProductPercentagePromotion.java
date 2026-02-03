package com.example.checkoutretail.promotion;

import com.example.checkoutretail.model.Cart;
import com.example.checkoutretail.model.CartItem;
import com.example.checkoutretail.model.DiscountDetail;
import com.example.checkoutretail.model.Product;
import com.example.checkoutretail.repository.Catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductPercentagePromotion extends Promotion {
    private final String sku;
    private final BigDecimal percent;

    public ProductPercentagePromotion(String sku, BigDecimal percent) {
        this.sku = sku;
        this.percent = percent;
    }

    @Override
    public List<DiscountDetail> apply(Cart cart) {
        List<DiscountDetail> result = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            if (item.getSku().equalsIgnoreCase(sku)) {
                Optional<Product> pOpt = Catalog.findBySku(sku);
                if (pOpt.isPresent()) {
                    BigDecimal price = pOpt.get().getPrice();
                    BigDecimal qty = new BigDecimal(item.getQuantity());
                    BigDecimal discount = price.multiply(qty).multiply(percent);
                    discount = discount.setScale(0, BigDecimal.ROUND_HALF_UP);

                    result.add(new DiscountDetail(
                            "Promo " + percent.multiply(new BigDecimal("100")).intValue() +
                                    "% en " + pOpt.get().getName(),
                            discount
                    ));
                }
            }
        }

        return result;
    }
}
