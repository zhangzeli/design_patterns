package com.observer.v1;

class Child {
    private boolean cry = false;
    private Dad dad = new Dad();
    private Mum mum = new Mum();
    private Dog dog = new Dog();

    public boolean isCry() {
        return cry;
    }

    public void  wekeup(){
        cry = true;
        dad.feed();
        dog.Wang();
        mum.hug();
    }
}

class Dad{
    public void feed(){
        System.out.println("dad feeding ...");
    }
}

class Mum{
    public void hug(){
        System.out.println("mum hugging ...");
    }
}

class Dog{
    public void Wang(){
        System.out.println("dog wang ...");
    }
}

public class Main{
    public static void main(String[] args) {

    }
}
