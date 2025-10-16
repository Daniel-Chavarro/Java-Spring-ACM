package org.qualifiers;


import org.qualifiers.service.order.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
class Runner {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
        OrderService orderService = context.getBean(OrderService.class);

        System.out.println("Enter the order amount:");
        double amount = scanner.nextDouble();

        orderService.placeOrder(amount);
        context.close();
    }
}