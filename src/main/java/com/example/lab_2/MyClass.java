package com.example.lab_2;

import java.util.Arrays;

class MyClass {

    @MyInject(time = BeanLifeTime.SEC)
    public String bean;

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getBean() {
        return bean;
    }
}
