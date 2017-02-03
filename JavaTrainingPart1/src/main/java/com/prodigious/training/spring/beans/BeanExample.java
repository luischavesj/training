package com.prodigious.training.spring.beans;

/**
 * Created by Luis Chaves on 1/27/2017
 * to create a bean.
 */
public class BeanExample {
    private String message;

    public BeanExample() {
        message = "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
