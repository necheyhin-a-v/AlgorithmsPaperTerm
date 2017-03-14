package com.company.Task4;

/**
 * Created by ALEKSANDR NECHEUKHIN on 21.12.2016.
 */
public class Main {
    public static void main(String[] argv) throws Exception {
        //Create tree
        BTree bTree = new BTree(2); //Create 2-3-4-... tree

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

        System.out.println("INITIAL");
        bTree.printByLayer();
/*
        bTree.delete(8);
        System.out.println("REMOVED 8");
        bTree.printByLayer();

        bTree.delete(1);
        System.out.println("REMOVED 1");
        bTree.printByLayer();

        bTree.delete(3);
        System.out.println("REMOVED 3");
        bTree.printByLayer();

        bTree.delete(-2);
        System.out.println("REMOVED -2");
        bTree.printByLayer();

        bTree.delete(-4);
        System.out.println("REMOVED -4");
        bTree.printByLayer();

        bTree.delete(-8);
        System.out.println("REMOVED -8");
        bTree.printByLayer();

        bTree.delete(4);
        System.out.println("REMOVED 4");
        bTree.printByLayer();

        bTree.delete(10);
        System.out.println("REMOVED 10");
        bTree.printByLayer();

        bTree.delete(11);
        System.out.println("REMOVED 11");
        bTree.printByLayer();


        bTree.delete(12);
        System.out.println("REMOVED 12");
        bTree.printByLayer();

        bTree.delete(5);
        System.out.println("REMOVED 5");
        bTree.printByLayer();

        bTree.delete(-1);
        System.out.println("REMOVED -1");
        bTree.printByLayer();

        bTree.delete(0);
        System.out.println("REMOVED 0");
        bTree.printByLayer();


        bTree.delete(2);
        System.out.println("REMOVED 2");
        bTree.printByLayer();
*/
    }
}
