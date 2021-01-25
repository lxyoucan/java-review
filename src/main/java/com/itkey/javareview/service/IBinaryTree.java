package com.itkey.javareview.service;

public interface IBinaryTree<E> {   //定义二叉树标准操作接口
    void add(E data);    //实现数据的增加
    int size();
    Object[] toArray();
    boolean contains(E data);    //进行数据查询
    void remove(E data);         //删除数据

    String guiString();        //转换成可以在tree-builder中展示的数据
}
