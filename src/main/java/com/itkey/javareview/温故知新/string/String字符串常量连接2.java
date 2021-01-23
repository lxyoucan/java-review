package com.itkey.javareview.温故知新.string;

public class String字符串常量连接2 {
    public static void main(String args[]) {
        String company = "yootk" ;
        String strA = "www.yootk.com" ; // 字符串常量
        String strB = "www." + company + ".com" ; // 字符串常量连接
        System.out.println(strA == strB) ;
    }
}
