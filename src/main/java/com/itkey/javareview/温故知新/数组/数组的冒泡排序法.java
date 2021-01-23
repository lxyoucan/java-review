package com.itkey.javareview.温故知新.数组;

public class 数组的冒泡排序法 {
    public static void main(String args[]) {
        int data [] = new int [] {3, 1, 5, 2, 8, 6, 9, 0} ;
        sort(data) ;
        printArray(data) ;
    }
    public static void sort(int array[]) { // 进行数组排序操作
        for (int x = 0 ; x < array.length ; x ++) {
            for (int y = 0 ; y < array.length - 1 ; y ++) {
                if (array[y] > array[y + 1]) {	// 前一个与后一个进行比较
                    int temp = array[y] ; // 引入一个第三方变量temp
                    array[y] = array[y + 1] ; // 修改“array[y]”的内容
                    array[y + 1] = temp ; // 获取原始的“array[y]”的内容
                }
            }
        }
    }
    public static void printArray(int temp[]) {
        for (int num : temp) {
            System.out.print(num + "、") ;
        }
    }
}
