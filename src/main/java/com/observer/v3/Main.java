package com.observer.v3;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    /**
     * 不同的观察者根据事件类型不同，做不同的处理
     * @param event
     */
    void action(Event event);
}

interface Subject {
    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void notifyObserver();
}

class Child implements Subject {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();

    public boolean isCry() {
        return cry;
    }

    private void wekeup() {
        cry = true;
        observers.forEach(o -> {
            Event e = new Event(System.currentTimeMillis(),"sleep",this);
            o.action(e);
        });
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        wekeup();
    }
}

/**
 * 事件对象
 */
class Event {
    /**
     * 事件发生事件
     */
    private long timestamp;
    /**
     * 事件名称，或者类型，可以定义枚举。咋这里为了掩饰方便
     */
    private String name;
    /**
     * 事件源对象
     */
    private Subject source;

    public Event(long timestamp, String name, Subject source) {
        this.timestamp = timestamp;
        this.name = name;
        this.source = source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public Subject getSource() {
        return source;
    }
}

class Dad implements Observer {
    private void feed() {
        System.out.println("dad feeding ...");
    }

    @Override
    public void action(Event event) {
        feed();
    }
}

class Mum implements Observer {
    private void hug() {
        System.out.println("mum hugging ...");
    }

    @Override
    public void action(Event event) {
        hug();
    }
}

class Dog implements Observer {
    private void wang() {
        System.out.println("dog wang ...");
    }

    @Override
    public void action(Event event) {
        wang();
    }
}

public class Main {
    public static void main(String[] args) {
        Subject subject = new Child();
        subject.addObserver(new Dad());
        subject.addObserver(new Mum());
        subject.addObserver(new Dog());
        subject.addObserver((e)->{
            System.out.println(e.getName());
            System.out.println(e.getTimestamp());
            System.out.println(e.getSource());
        });
        subject.notifyObserver();
    }
}