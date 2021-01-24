package com.itkey.javareview.温故知新.二叉树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        int line =5;
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<=line;i++){
            System.out.println("-----------------"+i+"-----------------");
            //2的i次方
            Double countDouble = Math.pow(2,i);
            int count = countDouble.intValue();
            for (int j = 0; j < count; j++) {
                System.out.print("*");
                list.add(j+1);
            }
            System.out.println();
        }
        System.out.println(list);
    }
}
