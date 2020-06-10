package com.cor.v6;

import java.util.ArrayList;
import java.util.List;

interface Filter{
    boolean doFilter(Request request,Response response,FilterChain filterChain);
}

class FilterChain implements Filter {
    List<Filter> filters = new  ArrayList<>();
    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;
    }
    int index = 0;

    public void deleteFilter(Filter filter){
        filters.remove(filter);
    }

    /**
     * 链条处理完
     */
    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain){
        if(index==filters.size()){return true;}
        Filter chain = filters.get(index);
        index++;
        chain.doFilter(request,response,filterChain);
        return true;
    }
}

class Request{
    String data="Request:";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class Response{
    String data="Response:";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class HTMLfilter implements Filter {

    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain) {
        request.setData(request.getData()+"HTMLfilter--");
        filterChain.doFilter(request, response, filterChain);
        response.setData(response.getData()+"HTMLfilter--");
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Request request,Response response,FilterChain filterChain) {
        request.setData(request.getData()+"SensitiveFilter--");
        filterChain.doFilter(request, response, filterChain);
        response.setData(response.getData()+"SensitiveFilter--");
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Response response = new Response();
        Request request = new Request();

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HTMLfilter()).addFilter(new SensitiveFilter());
        filterChain.doFilter(request,response,filterChain);
        System.out.println(request.getData());
        System.out.println(response.getData());
    }
}