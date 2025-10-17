package org.loop.withLoops;

import org.loop.withLoops.service.InventoryService;
import org.loop.withLoops.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Runner {

    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);

        Scanner scanner = new Scanner(System.in);
        OrderService orderService = context.getBean(OrderService.class);
        InventoryService inventoryService = context.getBean(InventoryService.class);

        System.out.println("Enter product name:");
        String productName = scanner.next();

        orderService.createOrder(productName);
        inventoryService.verifyIfOrderExits(productName);
    }
}
