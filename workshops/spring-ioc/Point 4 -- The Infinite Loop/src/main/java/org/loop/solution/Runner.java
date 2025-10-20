package org.loop.solution;

import org.loop.solution.service.InventoryService;
import org.loop.solution.service.OrderService;
import org.loop.solution.service.SalesManagerService;
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
        SalesManagerService salesManagerService = context.getBean(SalesManagerService.class);

        System.out.println("Enter product name:");
        String productName = scanner.next();

        salesManagerService.createOrder(productName);
        salesManagerService.verifyIfOrderExits(productName);
    }
}
