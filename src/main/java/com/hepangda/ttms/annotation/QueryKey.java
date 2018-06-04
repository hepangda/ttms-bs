package com.hepangda.ttms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface QueryKey {
    String value();
    boolean select() default true;
    boolean insert() default true;
    boolean delete() default false;
    boolean specialInsert() default false;
    String insertString() default "";
}
