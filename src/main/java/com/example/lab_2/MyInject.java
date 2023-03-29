package com.example.lab_2;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)/**/
public @interface MyInject {
    BeanLifeTime time() default BeanLifeTime.SEC;
}



