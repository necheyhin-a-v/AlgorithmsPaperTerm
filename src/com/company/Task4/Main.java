package com.company.Task4;

/**
 * Created by ALEKSANDR NECHEUKHIN on 21.12.2016.
 */
public class Main {
    public static void main(String[] argv) {
        //Create tree
        BTree bTree = new BTree(3); //Create 2-3 tree

        bTree.add(1);
        bTree.add(3);
        bTree.add(-2);
        bTree.add(8);
        bTree.add(4);
        bTree.add(6);
        bTree.add(-8);
        bTree.add(12);
        bTree.add(-4);
        bTree.add(-3);
        bTree.add(2);
        bTree.add(3);
        bTree.add(-1);
        bTree.add(4);
        bTree.add(1);
        bTree.add(5);
        bTree.add(10);
        bTree.add(3);
        bTree.add(-1);
        bTree.add(0);
        bTree.add(-4);
        bTree.add(-6);
        bTree.add(-8);
        bTree.add(-2);
        bTree.add(3);
        bTree.add(4);
        bTree.add(9);
        bTree.add(11);
        bTree.add(12);
        bTree.add(5);


        bTree.printByLayer();
    }
}
