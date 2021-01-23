package com.itkey.javareview.温故知新.数组;

public class 数组转置方法1容易产生垃圾 {
    public static void main(String args[]) {
        int data [] = new int [] {1, 2, 3, 4, 5, 6} ;
        data = reverse(data) ; // 传递data引用同时修改data引用
        printArray(data) ;
    }
    public static int [] reverse(int array[]) {
        int temp [] = new int [array.length] ; // 依据已有数组开辟新数组
        int foot = array.length - 1 ; // 索引从0开始
        for (int x = 0 ; x < temp.length ; x ++) {
            temp[x] = array[foot --] ; // 逆序插入
        }
        return temp ;
    }
    public static void printArray(int temp[]) {
        for (int num : temp) {
            System.out.print(num + "、") ;
        }
    }
}
