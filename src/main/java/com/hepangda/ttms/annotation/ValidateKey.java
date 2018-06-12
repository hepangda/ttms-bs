package com.hepangda.ttms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateKey {
    int maxLen() default 0;

    int minLen() default 0;     // 0代表不检查该项目

    int minRange() default 0;

    int maxRange() default 0;

    int errno(); // 0代表合法

    int spf() default -1;

    String[] enums() default "";
}
