package com.cor.v5;

import java.util.ArrayList;
import java.util.List;

class Msg{
    String name;
    String msg;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter{
    boolean doFilter(Msg msg);
}

class FilterChain implements Filter {
    List<Filter> filters = new  ArrayList<>();
    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;
    }

    public void deleteFilter(Filter filter){
        filters.remove(filter);
    }

    /**
     * 链条处理完
     */
    @Override
    public boolean doFilter(Msg msg){
        for ( Filter f : filters){
            boolean r = f.doFilter(msg);
            if(!r){
                return false;
            }
        }
        return true;
    }
}

class HTMLfilter implements Filter {

    @Override
    public boolean doFilter( Msg msg) {
        //第一步网页脚本
        String r = msg.getMsg();
        r = r.replace("<","&lt;");
        r = r.replace(">","&gt;");
        msg.setMsg(r);
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter( Msg msg) {
        String r = msg.getMsg();
        //第二步处理敏感词
        r = r.replace("台湾","中国");
        msg.setMsg(r);
        return true;
    }
}

class URLFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("zeze.info","^_^");
        msg.setMsg(r);
        return true;
    }
}


class SQLFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("OR","");
        r = r.replace("select","");
        msg.setMsg(r);
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        //在论坛发帖
        msg.setMsg("大家好：}<script> ,欢迎访问www.zeze.info 台湾 OR 1=1 ");

        FilterChain filterChain = new FilterChain();

        filterChain.addFilter(new HTMLfilter())
                .addFilter(new SensitiveFilter());

        FilterChain filterChain2 = new FilterChain();
        filterChain2.addFilter(new URLFilter());
        filterChain2.addFilter(new SQLFilter());
        filterChain.addFilter(filterChain2);

        System.out.println(msg);
    }
}