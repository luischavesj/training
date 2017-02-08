package com.prodigious.training.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Luis Chaves on 1/27/2017
 * to create a bean.
 */
public class BeanExample {
    private String message;
    private String name;
    private int id;//This is to test the set and get
    private Item itemUsa; //Only to test the @Autowired functionality

    private List addressList;
    private Map addressMap;
    private Set addressSet;
    private Properties addressProperties;

    public Item getItemUsa() {
        return itemUsa;
    }

    @Autowired
    public void setItemUsa(Item itemUsa) {
        this.itemUsa = itemUsa;
    }

    @PostConstruct
    public void init(){
        System.out.println("Just a friendly message to see the values of the bean after being constructed: " + this);
    }

    public Properties getAddressProperties() {
        return addressProperties;
    }

    public void setAddressProperties(Properties addressProperties) {
        this.addressProperties = addressProperties;
    }

    public List getAddressList() {
        return addressList;
    }

    public void setAddressList(List addressList) {
        this.addressList = addressList;
    }

    public Map getAddressMap() {
        return addressMap;
    }

    public void setAddressMap(Map addressMap) {
        this.addressMap = addressMap;
    }

    public Set getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set addressSet) {
        this.addressSet = addressSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanExample(String name) {

        this.name = name;
        this.message = "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Name = " + getName() + " Id = " + getId() + " Message = " + getMessage();
    }
}
