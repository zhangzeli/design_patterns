package com.observer.v2;

import java.util.ArrayList;
import java.util.List;

interface Observer{
    void action();
}
class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Dog());
    }

    public boolean isCry() {
        return cry;
    }

    public void  wekeup(){
        cry = true;
        observers.forEach(o->{
            o.action();
        });
    }
}

class Dad implements Observer{
    public void feed(){
        System.out.println("dad feeding ...");
    }

    @Override
    public void action() {
        feed();
    }
}

class Mum implements Observer{
    public void hug(){
        System.out.println("mum hugging ...");
    }

    @Override
    public void action() {
        hug();
    }
}

class Dog implements Observer{
    public void wang(){
        System.out.println("dog wang ...");
    }

    @Override
    public void action() {
        wang();
    }
}

public class Main{
    public static void main(String[] args) {
        Child child = new Child();
        child.wekeup();
    }
}
