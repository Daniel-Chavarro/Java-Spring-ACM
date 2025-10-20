package org.loop.solution.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public boolean checkInventory(String product) {
        System.out.println("Checking if Inventory exists for product " + product);
        return true;
    }

    public void verifyIfOrderExits(String product) {
        System.out.println("Order has been executed for product " + product);
    }
}
