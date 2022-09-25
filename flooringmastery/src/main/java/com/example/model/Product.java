package com.example.model;

import java.math.BigDecimal;

public class Product {
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getProcutType() {
        return this.productType;
    }
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }
    public BigDecimal getCostPerSquareFoot() {
        return this.costPerSquareFoot;
    }
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    public BigDecimal getLaborCostPerSquareFoot() {
        return this.laborCostPerSquareFoot;
    }

    public void setData(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot){
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
}
