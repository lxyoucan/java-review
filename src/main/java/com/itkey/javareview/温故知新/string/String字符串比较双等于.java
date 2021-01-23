package com.itkey.javareview.温故知新.string;

public class String字符串比较双等于 {
    public static void main(String args[]) {
        String strA = "yootk.com" ; // 字符串对象
        String strB = "yootk.com" ; // 直接赋值，内容相同
        System.out.println(strA == strB) ;	// 对象地址数值比较
        System.out.println(strA == "yootk.com") ; // 与匿名String对象进行地址数值比较
    }
}
