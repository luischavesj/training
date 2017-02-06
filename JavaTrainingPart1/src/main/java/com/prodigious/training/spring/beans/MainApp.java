package com.prodigious.training.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Luis Chaves on 1/27/2017
 * to text a quick bean example.
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new FileSystemXmlApplicationContext("Beans.xml");
        BeanExample beanExampleA =  (BeanExample) context.getBean("beanExample");
        System.out.println("From Configuration: " + beanExampleA.getMessage());

        beanExampleA.setMessage("Lets now change this message to another one using the letter \"A\"");
        System.out.println("From Java Code: " + beanExampleA.getMessage());
        BeanExample beanExampleB = (BeanExample) context.getBean("beanExample");

        System.out.println("From Configuration: " + beanExampleB.getMessage());
    }

}
