package com.itkey.javareview.controller;


import com.itkey.javareview.service.IBinaryTree;
import com.itkey.javareview.service.impl.BinaryTreeImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * 二叉树 相关的控制类
 * date : 2021-01-25 10:43:08
 * @author itkey
 */
@Controller
//@RequestMapping("/h5/")
public class BinaryTreeController {

    @RequestMapping("/binaryTree")
    public String binaryTree(ModelMap modelMap, String btData) {
        if(btData==null||btData.isEmpty()){
            btData ="10,5,6";
        }
        IBinaryTree<Integer> binaryTree = new BinaryTreeImpl<>();
        String numbers[] = btData.split(",");
        for (String numString :
                numbers) {
            binaryTree.add(Integer.parseInt(numString));
        }
        modelMap.put("btData",binaryTree.guiString());
        return "/binaryTree/index";
    }

    @RequestMapping("/binaryTree/help")
    public String binaryTreeHelp() {
        return "/binaryTree/help";
    }
}
