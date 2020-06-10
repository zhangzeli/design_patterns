package com.cor.v3;

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
    void doFilter( Msg msg);
}
class FilterChain{
    List< Filter> filters = new  ArrayList<>();
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public void deleteFilter(Filter filter){
        filters.remove(filter);
    }
    /**
     * 链条处理完
     */
    public void doFilter(Msg msg){
        for ( Filter f : filters){
            f.doFilter(msg);
        }
    }
}

class HTMLfilter implements Filter {

    @Override
    public void doFilter( Msg msg) {
        //第一步网页脚本
        String r = msg.getMsg();
        r = r.replace("<","&lt;");
        r = r.replace(">","&gt;");
        msg.setMsg(r);
    }
}

class SensitiveFilter implements Filter {

    @Override
    public void doFilter( Msg msg) {
        String r = msg.getMsg();
        //第二步处理敏感词
        r = r.replace("台湾","中国");
        msg.setMsg(r);
    }
}

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        //在论坛发帖
        msg.setMsg("大家好：}<script> ,欢迎访问www.zeze.info 台湾 ");

        FilterChain filterChain = new FilterChain();

        filterChain.addFilter(new HTMLfilter());
        filterChain.addFilter(new SensitiveFilter());
        filterChain.doFilter(msg);

        System.out.println(msg);
    }
}