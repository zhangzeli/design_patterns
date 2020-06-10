package com.cor.v1;

public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        //在论坛发帖
        msg.setMsg("大家好：}<script> ,欢迎访问www.zeze.info 台湾 ");

        //第一步网页脚本
        String r = msg.getMsg();
        r = r.replace("<","&lt;");
        r = r.replace(">","&gt;");
        msg.setMsg(r);

        //第二步处理敏感词
        r = r.replace("台湾","中国");
        msg.setMsg(r);
        System.out.println(msg);
    }
}

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
