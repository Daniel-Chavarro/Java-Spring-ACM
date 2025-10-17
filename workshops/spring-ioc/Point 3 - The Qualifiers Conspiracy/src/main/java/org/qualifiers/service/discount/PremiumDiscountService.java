package org.qualifiers.service.discount;

import org.springframework.stereotype.Service;

@Service
class PremiumDiscountService implements DiscountService {


    /**
     * Apply a premium discount of 10% to the given amount.
     *
     * @param amount the original amount
     * @return the amount after applying a 10% discount
     */
    @Override
    public double applyDiscount(double amount) {
        return amount * 90 / 100;
    }
}
