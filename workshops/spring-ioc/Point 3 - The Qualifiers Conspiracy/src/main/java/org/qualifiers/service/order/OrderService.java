package org.qualifiers.service.order;

import org.qualifiers.service.discount.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    /** The discount service to apply discounts. */
    private final DiscountService discountService;

    /**
     * Constructor for OrderService.
     * Uses @Qualifier to specify which DiscountService implementation to inject.
     * The injection is optional; if no bean is found, discountService will be null.
     *
     * @param discountService the discount service to apply discounts
     */
    @Autowired(required = false)
    public OrderService(@Qualifier("experimentalDiscountService") DiscountService discountService) {
        this.discountService = discountService;
    }


    /**
     * Default constructor for OrderService.
     * Sets discountService to null if no DiscountService bean is available.
     */
    public OrderService() {
        this.discountService = null;
    }

    /**
     * Place an order with the given amount, applying a discount if available.
     *
     * @param amount the original order amount
     */
    public void placeOrder(double amount) {
        if (discountService != null) {
            double discountedAmount = discountService.applyDiscount(amount);
            System.out.println("Order placed. Original amount: " + amount + ", Discounted amount: " + discountedAmount);
        } else {
            System.out.println("Order placed. Amount: " + amount + " (No discount applied)");
        }
    }
}
