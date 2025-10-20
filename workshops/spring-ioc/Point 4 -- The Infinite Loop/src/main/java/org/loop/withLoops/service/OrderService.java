package org.loop.withLoops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final InventoryService inventoryService;

    @Autowired
    public OrderService(InventoryService inventoryService) {
        System.out.println("Starting Order Service");
        this.inventoryService = inventoryService;
    }

    public void createOrder(String product) {
        if (inventoryService.checkInventory(product)) {
            System.out.println("Order has been created");
        } else  {
            System.out.println("Order has not been created, out of stock");
        }
    }

    public boolean verifyOrder(String product) {
        System.out.println("Checking if order exists for product " + product);
        return true;
    }
}
