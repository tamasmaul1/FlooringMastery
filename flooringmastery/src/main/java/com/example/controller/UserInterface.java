package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.Tax;
import com.example.service.DataService;

public class UserInterface {
    public static void run(){
        System.out.println("Program is starting, finding user data...");
        ArrayList<Tax> taxes = DataService.readTaxes();
        ArrayList<Order> orders = DataService.readOrders();
        ArrayList<Product> products = DataService.readProducts();
        UserInterface.printMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while(input != 6){
            UserInterface.runCommand(input, taxes, orders, products);
            UserInterface.printMenu();
            input = scanner.nextInt();
        }
        DataService.saveData(orders, products, taxes);
        scanner.close();
    }
    private static void runCommand(int input, ArrayList<Tax> taxes, ArrayList<Order> orders, ArrayList<Product> products) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        Scanner scanner = new Scanner(System.in);
        switch(input){
            case 1:{
                for(Order order : orders){
                    System.out.println(order.getData());
                }
            }
            case 2:{
                System.out.println("Please enter the details of the order: ");
                System.out.print("customerName,state,productType,area");
                String orderData = scanner.nextLine();
                String[] data = orderData.split(",",4);
                Order order = new Order();
                int ordernum = orders.size()+1;
                String date = now.format(formatter);
                order.construct(ordernum, data, taxes, products, date);
            }
            case 3:{
                System.out.println("Please enter the orderNumber you want to edit: ");
                int ordernum = scanner.nextInt();
                if(ordernum<orders.size()){
                    for(Order order : orders){
                        if(order.getOrderNumber() == ordernum){
                            System.out.println("The current data of the order is: ");
                            System.out.println(order.getData());
                            System.out.println("If you want to change the data enter the following data in the same order: customerName,state,productType,area");
                            String orderData = scanner.nextLine();
                            String[] data = orderData.split(",",4);
                            String date = now.format(formatter);
                            order.construct(ordernum, data, taxes, products, date);
                        }
                    }
                }
                else{System.out.println("Order number does not exist, you only have " + orders.size() + " orders right now.");}
            }
            case 4:{
                System.out.println("Please enter the orderNumber of the order you want to remove");
                int ordernum = scanner.nextInt();
                if(ordernum<orders.size()){
                    for(Order order:orders){
                        if(order.getOrderNumber() == ordernum){
                            int newnum = 0;
                            for(int i = 0; i<orders.size(); i++){
                                if(newnum!=0){
                                    orders.get(i).setOrderNumber(newnum);
                                }
                                if(orders.get(i).getOrderNumber() == ordernum){
                                    newnum = orders.get(i).getOrderNumber();
                                }
                            }
                            orders.remove(order);
                        }
                    }

                }
                else{System.out.println("Order number does not exist, you only have " + orders.size() + " orders right now.");}
            }
            case 5:{
                DataService.exportOrders(orders);
            }
        }
        scanner.close();
    }
    public static void printMenu(){
        System.out.println("All data has been loaded");
        System.out.println("* * * * * * * * * * * * * * * * * *");
        System.out.println(" * <<FLOORING PROGRAM>>");
        System.out.println(" * 1. Display Orders");
        System.out.println(" * 2. Add an order");
        System.out.println(" * 3. Edit an order");
        System.out.println(" * 4. Remove an order");
        System.out.println(" * 5. Export all data");
        System.out.println(" * 6. Quit");
        System.out.println(" *");
        System.out.println(" * * * * * * * * * * * * * * * * * *");
    }
}
