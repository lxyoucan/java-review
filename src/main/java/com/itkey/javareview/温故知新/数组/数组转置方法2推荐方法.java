package com.itkey.javareview.温故知新.数组;

public class 数组转置方法2推荐方法 {
    public static void main(String args[]) {
        int data[] = new int[]{1, 2, 3, 4, 5, 6};
        reverse(data); // 数组转置
        printArray(data);
    }

    public static void reverse(int array[]) {
        int center = array.length / 2; // 计算循环次数
        int head = 0; // 前部的操作脚标
        int tail = array.length - 1; // 后部操作脚标
        for (int x = 0; x < center; x++) {    // 转置交换
            int temp = array[head];
            array[head] = array[tail];
            array[tail] = temp;
            head++;
            tail--;
        }
    }

    public static void printArray(int temp[]) {
        for (int num : temp) {
            System.out.print(num + "、");
        }
    }

}
