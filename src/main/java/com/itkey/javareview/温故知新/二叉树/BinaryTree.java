package com.itkey.javareview.温故知新.二叉树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface IBinaryTree<E> {      //定义二叉树标准操作接口
    void add(E data);    //实现数据的增加
    int size();
    Object[] toArray();
    boolean contains(E data);    //进行数据查询
    void remove(E data);         //删除数据

    String guiString();        //转换成可以在tree-builder中展示的数据
}

class BinaryTreeImpl<E> implements IBinaryTree<E>{

    private class Node{         //该内部类只为当前的外部类提供服务
        private Comparable<E> data;     //要存储的数据，全部进行强制性转型
        private Node left;          //左子树
        private Node right;         //右子树
        private Node parent;        //父节点
        public Node(Comparable<E> data){        //节点创建的同时保存数据
            this.data = data;
        }

        public void toArrayNode(){              //实现数据的递归
            if(this.left != null){              //此时存在有左子树
                this.left.toArrayNode();    //递归调用
            }
            BinaryTreeImpl.this.data[BinaryTreeImpl.this.foot ++] = (E)this.data;
            if(this.right !=null){              //此时存在有右子树
                this.right.toArrayNode();       //递归调用
            }
        }

        public void toGUIString(){              //实现数据的递归

            //BinaryTreeImpl.this.data[BinaryTreeImpl.this.foot ++] = (E)this.data;
            //根节点为0级，判断当前节点的层级，实际就是判断到根节点的距离
            int line = 0;
            Node currentNode = this;
            while (currentNode.parent!=null){
                currentNode = currentNode.parent;
                line++;
            }
            System.out.println("*******node:"+this.data+"为第"+line+"层");
            while (BinaryTreeImpl.this.lists.size()<line+1+1){      //考虑到会赋值一下级
                BinaryTreeImpl.this.lists.add(new ArrayList());
            }

            List list = BinaryTreeImpl.this.lists.get(line);
            list.add(this.data);

            if(this.left != null){              //此时存在有左子树
                this.left.toGUIString();    //递归调用
            }else if(this.right!=null){         //右节点不为空，则需要输出null，不然也不需要null
                //如果不存在说明下一级，则为null
                list = BinaryTreeImpl.this.lists.get(line+1);
                list.add(null);
            }

            if(this.right !=null){              //此时存在有右子树
                this.right.toGUIString();       //递归调用
            }else if(this.left!=null){          //左节点不为空，才输出null
                //如果不存在说明下一级，则为null
                list = BinaryTreeImpl.this.lists.get(line+1);
                list.add(null);
            }
        }

        public Node containsNode(E data){
            if(this.data.compareTo(data)==0){       //数据相同
                return this;        //返回当前的节点
            } else{
                if(this.data.compareTo(data)<0){    //当前的节点小于判断的数据
                    if(this.right!=null){       //存在有右节点
                        return this.right.containsNode(data);       //继续判断后续节点
                    }else {
                        return null;
                    }
                } else {
                    if(this.left !=null){
                        return this.left.containsNode(data);
                    }else {
                        return null;
                    }
                }
            }
        }

    }


    // ----------------以下的操作为二叉树实现结构----------------
    private Node root;      //数据结构都需要提供有根节点
    private int count;  // 保存数据的个数
    private int foot;       //描述的是数组的索引
    private Object [] data;     //返回的对象数组

    private List<List<Object>> lists = new ArrayList<>();
    @Override
    public void add(E data) {
        if(data ==null ){
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null.");
        }
        if(!(data instanceof  Comparable)){
            throw new ClassCastException("要保存数据对象所在的类没有实现java.lang.Comparable接口。");
        }
        Node newNode = new Node((Comparable) data);         //将数据保存在节点之中
        if(this.root ==null){           //当前没有根节点存在
            this.root = newNode;        //保存根节点
            count++;
        } else {            //需要确定节点的存储位置
            Node currentNode = this.root;   //设置当前节点
            while (currentNode!=newNode){      //当前节点不是新节点，表示新的节点未保存
                if(currentNode.data.compareTo(data)<=0){        //比根节点大
                    if(currentNode.right !=null){       //当前节点存在有右节点
                        currentNode = currentNode.right;        //修改当前的操作节点
                    }else{
                        currentNode.right = newNode;    //将新节点保存在右节点
                        newNode.parent = currentNode;   //设置父节点
                        currentNode = newNode;          //结束循环
                        count++;
                    }
                }else{          //要小于根节点， 所以要放在根节点的左边
                    if(currentNode.left !=null ){
                        currentNode = currentNode.left;     //设置当前藬为左节点
                    }else{  //没有左节点
                        currentNode.left = newNode;     //保存新节点
                        newNode.parent = currentNode;   //设置父节点
                        currentNode = newNode;
                        count++;
                    }
                }
            }
        }
    }


    @Override
    public int size() {
        return this.count;
    }

    @Override
    public Object[] toArray() {
        if(this.size()==0){
            return  null;
        }
        this.data = new Object[this.size()];        //实例化新的对象数组
        this.foot = 0;      //角标清零
        this.root.toArrayNode();        //获取所有节点数据
        return this.data;
    }

    @Override
    public boolean contains(E data) {
        if(data ==null ){
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null.");
        }
        if(!(data instanceof  Comparable)){
            throw new ClassCastException("要保存数据对象所在的类没有实现java.lang.Comparable接口。");
        }
        if(this.size() ==0 ){       //没有任何的数据保存
            return false;
        }
        return this.root.containsNode(data) != null;        //返回的节点不为空存在有数据
    }

    @Override
    public void remove(E data) {
        if(data ==null ){
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null.");
        }
        if(!(data instanceof  Comparable)){
            throw new ClassCastException("要保存数据对象所在的类没有实现java.lang.Comparable接口。");
        }

        if(this.contains(data)){        //判断是否为根节点
            if(this.root.data.compareTo(data)==0){
                this.root = this.moveNode(data);        //移动节点
            }else {
                this.moveNode(data);
            }
            this.count--;
        }

    }
    private Node moveNode(E data){                          //实现节点移动
        Node moveSubNode = null;                            //假设当前的节点为要移动的子节点
        Node deleteNode = this.root.containsNode(data);     //首先要判断删除节点是否存在
        if(deleteNode == null){     //不存在删除节点
            return null;            //没有要移动的节点
        }

        //情况一：要删除的节点没有任何的子节点存在
        if(deleteNode.left == null && deleteNode.right == null){
            //
            if(deleteNode.parent !=null){   //存在有删除节点的父节点引用，那么就是指非根节点
                if(deleteNode.parent.data.compareTo(data)<=0){      //说明是右节点
                    deleteNode.parent.right = null;
                }else{
                    deleteNode.parent.left = null;
                }
            }
            deleteNode.parent = null;       //处理的是根节点，则取消根节点的父节点
        }

        //情况二：如果待删除节点只有一个子节点，那么直接删掉，并用其子节点去顶替它
        if((deleteNode.left!=null & deleteNode.right==null)|| (deleteNode.left == null&deleteNode.right!=null)){    //存在有一个子节点
            moveSubNode = null; //要移动的节点，可能是左节点也可能是右节点
            if(deleteNode.left !=null)  {       //存在删掉节点的左节点
                moveSubNode = deleteNode.left;      //确定了节点的位置
            }else {             //删除的节点不存在有左节点
                moveSubNode = deleteNode.right;     //确定移动的节点位置
            }
            if(deleteNode.parent != null){          //删除节点存在父节点
                if((deleteNode.parent.data.compareTo(data)<=0)){     //右节点数据
                    deleteNode.parent.right = moveSubNode;      //节点移动
                }else {     //左节点
                    deleteNode.parent.left = moveSubNode;           //节点移动
                }
            }
            moveSubNode.parent = deleteNode.parent;     //修改父节点

        }

        //情况三：如果待删除节点有两个节点，就需要确定右子树中最小节点
        if(deleteNode.left!=null&& deleteNode.right !=null){
            moveSubNode = deleteNode.right;         //要移动的节点设置为删除节点的右节点
            while (moveSubNode.left !=null ){       //找到右边的最左节点
                moveSubNode = moveSubNode.left;     //找到最左节点
            }

            if(moveSubNode.right != null){
                //moveSubNode.parent.left = moveSubNode.right;        //修改节点的位置
                //moveSubNode.right.parent = moveSubNode.parent;
            }else {
                if(deleteNode.right != moveSubNode){        //删除节点的右节点不是移动节点
                    moveSubNode.parent.left = null;     //删除移动节点对应的左节点
                }
            }

            moveSubNode.parent = deleteNode.parent;     //修改移动节点的父节点
            moveSubNode.left = deleteNode.left;         //修改移动节点的左节点

            if(deleteNode.right != moveSubNode){        //考虑到右节点的问题
                moveSubNode.right = deleteNode.right;
            }
            //判断保存在左 还是右
            if(deleteNode.parent != null){          //存在有删除节点
                if(deleteNode.parent.data.compareTo(data)<=0){
                    deleteNode.parent.right = moveSubNode;
                }else {
                    deleteNode.parent.left = moveSubNode;
                }

            }

        }


        return moveSubNode;     //返回移动的子节点，因为需要考虑到根节点的删除问题
    }

    /**
     * 按照固有格式输出
     * 2的n次方的形式输出二叉树节点,如果节点不存在则指定位置为null
     * 比如：
     * 第1行：  a
     * 第2行： b  c
     * 第3行：d e null g
     * @return
     */
    @Override
    public String guiString() {
        Node currentNode = this.root;
        currentNode.toGUIString();
        List result = new ArrayList();      //不好判断数据大小，所以这里用List了
        for (List list :
                this.lists) {
            for (Object obj :
                    list) {
                result.add(obj);
            }
        }
        String resultStr = result.toString();
        resultStr = resultStr.replaceAll("\\s","");
        return resultStr;
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        IBinaryTree<Integer> binaryTree = new BinaryTreeImpl<>();
        //int numbers[] = new int[] {80,50,90,55,30,60,10,85,95};
        //int numbers[] = new int[] {80,50,90,30,85,95,60,10};
        //int numbers[] = new int[] {80,50,90,95,85,60,30,10,55,70};
        int numbers[] = new int[] {80,90,95,85,60,30,10,55,70};
        for (int num :
                numbers) {
            binaryTree.add(num);
        }
        //binaryTree.remove(50);
        //binaryTree.remove(80);
        System.out.println("【获取全部数据】"+ Arrays.toString(binaryTree.toArray()));

        System.out.println(binaryTree.guiString());;
    }
}
