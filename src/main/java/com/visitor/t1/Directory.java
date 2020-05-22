package com.visitor.t1;

import java.util.ArrayList;
import java.util.Iterator;

public class Directory  extends Entry{

    String name;
    ArrayList entrys=new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator it = entrys.iterator();
        while (it.hasNext()){
            size += ((Entry)it.next()).getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        entrys.add(entry);
        return this;
    }

    @Override
    public Iterator iterator() {
        return entrys.iterator();
    }


    @Override
    public void printList(String prefix) {
        System.out.println(prefix+"/"+this);
        Iterator it=entrys.iterator();
        Entry entry;
        while(it.hasNext()){
            entry=(Entry)it.next();
            entry.printList(prefix+"/"+name);
        }
    }

    @Override
    public void accept(Visitor visitor) {
//          System.out.println("开始访问文件夹:"+this);
        visitor.visit(this);
//           System.out.println("结束访问文件夹:"+this);
        //   System.out.println();
    }
}
