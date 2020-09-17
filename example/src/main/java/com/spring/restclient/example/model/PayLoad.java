package com.spring.restclient.example.model;

/**
 * @author Houston(Nayana)
 **/

public class PayLoad {

    String attribute;

    public PayLoad() {
    }

    public PayLoad(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "PayLoad{" +
                "attribute='" + attribute + '\'' +
                '}';
    }
}
