package com.example;

import com.example.service.DataService;

public final class App {
    public static void main(String[] args) {
        System.out.println(DataService.readOrders());
    }
}
