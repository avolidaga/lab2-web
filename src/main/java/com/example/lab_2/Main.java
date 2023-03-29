package com.example.lab_2;

import java.util.Date;

class Main {
    public static void main(String[] args) throws  NoSuchFieldException {

        MyClass mc = new MyClass();
        long initTime = new Date().getTime();
        mc.setBean("Gryffindor");
        System.out.println(mc.getBean());
        MyInject a = mc.getClass().getField("bean").getDeclaredAnnotation(MyInject.class);

        while (true) {
            long curTime = new Date().getTime();
            if (a.time() == BeanLifeTime.SEC) {
                if ((curTime - initTime) == 1000) {
                    mc.setBean("Weasley");
                    System.out.println(mc.getBean());
                    initTime = curTime;
                }
            }else if (a.time() == BeanLifeTime.MIN) {
                if ((curTime - initTime) == 1000*60) {
                    mc.setBean("Weasley");
                    System.out.println(mc.getBean());
                    initTime = curTime;
                }
            } else {
                if ((curTime - initTime) == 1000*60*60) {
                    mc.setBean("Weasley");
                    System.out.println(mc.getBean());
                    initTime = curTime;
                }
            }
        }
    }
}
