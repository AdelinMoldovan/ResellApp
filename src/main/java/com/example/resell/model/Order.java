package com.example.resell.model;

import java.io.Serializable;

public class Order implements Serializable {

    private int id;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
