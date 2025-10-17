package org.qualifiers.service.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
class BasicDiscountService implements DiscountService {

    /**
     * Apply a basic discount of 5% to the given amount.
     *
     * @param amount the original amount
     * @return the amount after applying a 5% discount
     */
    @Override
    public double applyDiscount(double amount) {
        return amount * 95 / 100;
    }
}
