package com.demoselenium.Clases;

public class Novel {
    private String name;
    private String price;
    private String quantity;

    public Novel(String name, String price, String quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public String getPrice(){
        return this.price;
    }

    public String getQuantity(){
        return this.quantity;
    }
}
