package com.company.Task4;

/**
 * BTREE NODE for using by BTREE CLASS
 */
class BTreeNode<Type> {
    public boolean Leaf;                //Is the node leaf
    public int Size;                    //Current values count
    public Comparable<Type>[] Values;   //Values (keys)
    public BTreeNode[] Children;        //Pointers

    /////////////////////////////////////////////
    //Constructor
    //parameter "t" is the minimum degree of tree
    //t=1 - binary TREE
    //t=2 - 2-3 TREE
    //etc.
    ////////////////////////////////////////////
    BTreeNode(int t) {
        int Size = 0;

        //one extra place in case of sharing value, pretending overflow
        this.Children = new BTreeNode[2 * t + 1];
        this.Leaf = true;
        this.Values = new Comparable[2 * t];
    }

    ///////////////////////////////
    //Returns true if the node full
    ///////////////////////////////
    boolean isFull() {
        if (this.Size == Values.length - 1)//-1 because one is extra (see constructor)
            return true;
        else
            return false;
    }
}
