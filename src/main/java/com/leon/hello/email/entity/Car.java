package com.leon.hello.email.entity;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: Car
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/17 21:17
 * @Version 1.0
 * @DESCRIPTION: TODO
 **/
public class Car {

    private String name;
    private int price;

    public Car() {
    }

    public Car(String name, int price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
