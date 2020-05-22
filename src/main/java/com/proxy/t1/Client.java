package com.proxy.t1;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        List<String> proxynames = new ArrayList<>();//load(config.yml)
        proxynames.add("com.proxy.t1.PayServiceProxy");
        proxynames.add("com.proxy.t1.PayServiceTimeProxy");
//        proxynames.forEach(it->{
//            try {
//                Class<?> aClass = Class.forName(it);
//                Object o = aClass.newInstance();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        });
    }
}
