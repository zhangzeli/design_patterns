package com.visitor.t1;

public class File extends  Entry {

    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix+"/"+this);
    }

    @Override
    public void accept(Visitor visitor) {
//        System.out.println("开始访问文件："+this);
        visitor.visit(this);
//        System.out.println("结束访问文件："+this);
    }
}
