package org.loop.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesManagerService {
    private final InventoryService inventoryService;
    private final OrderService orderService;

    @Autowired
    public SalesManagerService(InventoryService inventoryService, OrderService orderService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    public void createOrder(String product) {
        if (inventoryService.checkInventory(product)) {
            orderService.createOrder(product);
        } else {
            System.out.println("Order has not been created, out of stock");
        }
    }

    public void verifyIfOrderExits(String product) {
        if (orderService.verifyOrder(product)) {
            inventoryService.verifyIfOrderExits(product);
        } else {
            System.out.println("Failed to verify if order exits");
        }
    }
}
