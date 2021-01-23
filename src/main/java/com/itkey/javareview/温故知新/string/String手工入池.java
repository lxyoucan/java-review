package com.itkey.javareview.温故知新.string;

public class String手工入池 {
    public static void main(String args[]) {
        String strA = "yootk.com" ; // 直接赋值
        String strB = new String("yootk.com").intern() ; // 手工入池
        System.out.println(strA == strB) ; // 地址数值比较
    }
}
