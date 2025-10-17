package org.qualifiers.service.discount;


public interface DiscountService {

    /**
     * Apply discount to the given amount.
     *
     * @param amount the original amount
     * @return the amount after applying discount
     */
    double applyDiscount(double amount);
}
