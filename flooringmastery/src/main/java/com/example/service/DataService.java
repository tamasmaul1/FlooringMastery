package com.example.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.Tax;

public class DataService {
    public static ArrayList<Product> readProducts(){
        try{
            File file = new File("flooringmastery/FileData/Data/Products.txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                Scanner scanner = new Scanner(fr);
                ArrayList<Product> products = new ArrayList<Product>();
                boolean firstline = true;
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(!firstline){
                        String[] data = line.split(",",3);
                        Product product = new Product();
                        product.setData(data[0], new BigDecimal(data[1]), new BigDecimal(data[2]));
                        products.add(product);
                    }
                    else{
                        firstline = false;
                    }
                    
                }
                scanner.close();
                
                return products;
            }
        }
        catch(IOException e){
            System.out.println("IOException Occured");
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static ArrayList<Tax> readTaxes(){
        try{
            File file = new File("flooringmastery/FileData/Data/Taxes.txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                Scanner scanner = new Scanner(fr);
                ArrayList<Tax> taxes = new ArrayList<Tax>();
                boolean firstline = true;
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(!firstline){
                        String[] data = line.split(",",3);
                        Tax tax = new Tax();
                        tax.setData(data[0], data[1], new BigDecimal(data[2]));
                        taxes.add(tax);
                    }
                    else{
                        firstline = false;
                    }
                    
                }
                scanner.close();
                
                return taxes;
            }
        }
        catch(IOException e){
            System.out.println("IOException Occured");
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static ArrayList<Order> readOrders(){
        try{
            String orderPath = "flooringmastery/FileData/Orders";
            File[] files = new File(orderPath).listFiles();
            ArrayList<String> fileNames = new ArrayList<String>();
            for(File file :files){
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
            ArrayList<Order> orders = new ArrayList<Order>();
            for(String fileName : fileNames){
                File file = new File(orderPath+ "/" +fileName);
                FileReader fr = new FileReader(file);
                Scanner scanner = new Scanner(fr);
                boolean firstline = true;
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(!firstline){
                        String[] data = line.split(",",12);
                        String dateData = fileName.split("_",2)[1].split(".txt",2)[0];
                        String dateDay = dateData.substring(0,2);
                        String dateMonth = dateData.substring(2,4);
                        String dateYear = dateData.substring(4,8);
                        String date = dateDay + "-" + dateMonth + "-" + dateYear;
                        Order order = new Order();
                        order.setData(Integer.valueOf(data[0]), data[1], data[2], new BigDecimal(data[3]),data[4],new BigDecimal(data[5]),new BigDecimal(data[6]),new BigDecimal(data[7]),new BigDecimal(data[8]),new BigDecimal(data[9]),new BigDecimal(data[10]),new BigDecimal(data[11]), date);
                        orders.add(order);
                    }
                    else{
                        firstline = false;
                    }
                    
                }
                scanner.close();
            }
            return orders;
        }
        catch(Exception e){
            System.out.println("IOException Occured");
            e.printStackTrace();
            return null;
        }
    }
    public static void exportOrders(ArrayList<Order> orders){
        String orderPath = "flooringmastery/FileData/Orders";
        File[] files = new File(orderPath).listFiles();
        ArrayList<String> fileDates = new ArrayList<String>();
        for(File file :files){
            if (file.isFile()) {
                String fileName = file.getName();
                String dateData = fileName.split("_",2)[1].split(".txt",2)[0];
                String dateDay = dateData.substring(0,2);
                String dateMonth = dateData.substring(2,4);
                String dateYear = dateData.substring(4,8);
                String date = dateDay + "-" + dateMonth + "-" + dateYear;
                fileDates.add(date);
            }
        }
        for(Order order : orders){
            try{
                File file = new File(orderPath + "/" + "Orders_" + order.getFileDate());
                if(file.exists()){
                    FileWriter fw = new FileWriter(file, true);
                    fw.write(order.getData());
                    fw.close();
                }
                else{
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    fw.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
                    fw.write(order.getData());
                    fw.close();
                }
            }
            catch(IOException e){
                System.out.println("IOException Occured");
                    e.printStackTrace();
            }
        }
    }
    public static void saveData(ArrayList<Order> orders, ArrayList<Product> products, ArrayList<Tax> taxes) {

    }
}
