package com.example.PetShop.jdbc.model;

public class TopName {

    private String name;
    private long counter;

    public TopName() {
    }

    public TopName(String name, long counter) {
        this.name = name;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "topName{" +
                "name='" + name + '\'' +
                ", counter=" + counter +
                '}';
    }
}
