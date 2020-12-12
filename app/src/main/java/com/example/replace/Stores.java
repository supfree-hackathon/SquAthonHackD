package com.example.replace;

public class Stores {

    private String name;
    private double lan;
    private double lon;

    public Stores(String name, double lan, double lon){

    this.name = name;
    this.lan = lan;
    this.lon = lon;

    }

    public String getName(){
        return name;
    }
    public double getLan(){
        return lan;
    }
    public double getLon(){
        return lon;
    }

}
