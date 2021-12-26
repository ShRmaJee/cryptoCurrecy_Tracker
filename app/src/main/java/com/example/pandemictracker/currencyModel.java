package com.example.pandemictracker;

public class currencyModel {
    private String name;
    private String symb;
    private double currency;
    private String max_supply;
    private String circulating_supply;
    private String total_supply;
    private String volume;
    private String market_cap;
    private String volume_change_24h;
    private String percent_change_7d;

    private boolean expanded;

    public currencyModel(String name, String symb, double currency, String max_supply, String circulating_supply, String total_supply, String volume, String market_cap, String volume_change_24h, String percent_change_7d) {
        this.name = name;
        this.symb = symb;
        this.currency = currency;
        this.max_supply = max_supply;
        this.circulating_supply = circulating_supply;
        this.total_supply = total_supply;
        this.volume = volume;
        this.market_cap = market_cap;
        this.volume_change_24h = volume_change_24h;
        this.percent_change_7d = percent_change_7d;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

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

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(String circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getVolume_change_24h() {
        return volume_change_24h;
    }

    public void setVolume_change_24h(String volume_change_24h) {
        this.volume_change_24h = volume_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }
}