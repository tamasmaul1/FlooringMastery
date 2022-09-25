package com.example.model;

import java.math.BigDecimal;

public class Tax {
    private String stateID;
    private String stateName;
    private BigDecimal taxRate;

    public String getStateID(){
        return this.stateID;
    }
    public String getStateName(){
        return this.stateName;
    }
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }

    public void setData(String stateID, String stateName, BigDecimal taxRate){
        this.stateID = stateID;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }
    
}
