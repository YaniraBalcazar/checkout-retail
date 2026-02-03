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


public class BuyGetFreePromotion extends Promotion {
    private final String sku;
    private final int x;
    private final int y;

    public BuyGetFreePromotion(String sku, int x, int y) {
        this.sku = sku;
        this.x = x;
        this.y = y;
    }

    @Override
    public List<DiscountDetail> apply(Cart cart) {
        List<DiscountDetail> result = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            if (item.getSku().equalsIgnoreCase(sku)) {
                int qty = Math.toIntExact(item.getQuantity());
                if (qty >= x + y) {
                    Optional<Product> pOpt = Catalog.findBySku(sku);
                    if (pOpt.isPresent()) {
                        int groups = qty / (x + y);
                        int freeUnits = groups * y;

                        BigDecimal discount = pOpt.get().getPrice()
                                .multiply(new BigDecimal(freeUnits))
                                .setScale(0, BigDecimal.ROUND_HALF_UP);

                        result.add(new DiscountDetail(
                                "Promo compra " + x + " lleva " + y + " gratis en " + pOpt.get().getName(),
                                discount
                        ));
                    }
                }
            }
        }

        return result;
    }
}
