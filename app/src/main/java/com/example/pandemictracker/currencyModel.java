package com.example.pandemictracker;

public class currencyModel {
    private String name;
    private String symb;
    private double currency;
    private int delta;

    public currencyModel(String name, String symb, double currency, int delta) {
        this.name = name;
        this.symb = symb;
        this.currency = currency;
        this.delta = delta;
    }
    //getter and setters for adapter class.
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymb() {
        return symb;
    }

    public void setSymb(String symb) {
        this.symb = symb;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }
}
