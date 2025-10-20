package org.loop.solution.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createOrder(String product) {
        System.out.println("Order has been created");
    }

    public boolean verifyOrder(String product) {
        System.out.println("Checking if order exists for product " + product);
        return true;
    }
}
