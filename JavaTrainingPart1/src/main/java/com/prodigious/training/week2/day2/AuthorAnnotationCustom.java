package com.prodigious.training.week2.day2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Luis Chaves on 1/26/2017
 * to test the annotations.
 */
public class AuthorAnnotationCustom {

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.TYPE,ElementType.METHOD})
    public @interface AuthorAnnotation{
        String AuthorName();
        String EmailId();
        String EmployeeType() default "permanent";
    }
}
