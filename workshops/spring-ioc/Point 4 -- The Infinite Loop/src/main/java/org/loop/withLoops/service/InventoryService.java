package org.loop.withLoops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final OrderService orderService;

    @Lazy
    @Autowired
    public InventoryService(OrderService orderService) {
        System.out.println("Starting Inventory Service");
        this.orderService = orderService;
    }

    public boolean checkInventory(String product) {
        System.out.println("Checking if Inventory exists for product " + product);
        return true;
    }

    public void verifyIfOrderExits(String product) {
        if (orderService.verifyOrder(product)) {
            System.out.println("Order has been executed for product " + product);
        } else  {
            System.out.println("Order has been cancelled for product " + product);
        }
    }
}
