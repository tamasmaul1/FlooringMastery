package com.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private String date;

    public String getCustomerName() {
        return this.customerName;
    }

    public String getDate(){
        return this.date;
    }

    public String getFileDate(){
        String dateDay = this.date.substring(0,2);
        String dateMonth = this.date.substring(3,5);
        String dateYear = this.date.substring(6);
        return dateDay+dateMonth+dateYear;
    }

    public String getData(){
        String orderNumber = String.valueOf(this.orderNumber);
        String taxRate = String.valueOf(this.taxRate);
        String area = String.valueOf(this.area);
        String costPerSquareFoot = String.valueOf(this.costPerSquareFoot);
        String laborCostPerSquareFoot = String.valueOf(this. laborCostPerSquareFoot);
        String materialCost = String.valueOf(this.materialCost);
        String laborCost = String.valueOf(this.laborCost);
        String tax = String.valueOf(this.tax);
        String total = String.valueOf(this.total);
        String result = orderNumber + ","+ this.customerName+","+this.state+","+taxRate+","+this.productType+","+area+","+costPerSquareFoot+","+laborCostPerSquareFoot+","+materialCost+","+laborCost+","+tax+","+total;
        return result;
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setData(int orderNumber, String customerName, String state, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, BigDecimal laberCost, BigDecimal tax, BigDecimal total, String date){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laberCost;
        this.tax = tax;
        this.total = total;
    }

    public void construct(int ordernum, String[] data, ArrayList<Tax> taxes, ArrayList<Product> products, String date) {
        this.orderNumber = ordernum;
        this.customerName = data[0];
        this.state = data[1];
        this.productType = data[2];
        this.area = new BigDecimal(data[3]);
        for(Tax tax : taxes) {
            if(tax.getStateID().equals(this.state)){
                this.taxRate = tax.getTaxRate();
            }
        }
        for(Product product : products){
            if(product.getProcutType().equals(this.productType)){
                this.costPerSquareFoot = product.getCostPerSquareFoot();
                this.laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
            }
        }
        this.materialCost = this.area.multiply(this.costPerSquareFoot);
        this.laborCost = this.area.multiply(this.laborCostPerSquareFoot);
        this.tax = this.materialCost.add(this.laborCost).multiply(this.taxRate.divide(new BigDecimal(100)));
        this.total = this.materialCost.add(this.laborCost.add(this.tax));
        this.date = date;
    }
    
}
