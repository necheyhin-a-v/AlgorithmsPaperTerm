package com.company.Task4;

/**
 * BTREE NODE for using by BTREE CLASS
 */
class BTreeNode<Type> {
    public boolean Leaf;                //Is the node leaf
    public int Size;                    //Current values count
    public Comparable<Type>[] Values;   //Values (keys)
    public BTreeNode[] Children;        //Pointers
    private int T;                      //Degree of tree

    /////////////////////////////////////////////
    //Constructor
    //parameter "t" is the minimum degree of tree
    //t=1 - binary TREE
    //t=2 - 2-3 TREE
    //etc.
    ////////////////////////////////////////////
    BTreeNode(int t) {
        int Size = 0;
        this.Children = new BTreeNode[2 * t];
        this.Leaf = true;
        this.Values = new Comparable[2 * t - 1];//(Comparable<Type>[]) new Object[]();
        this.T = t;
    }

    ///////////////////////////////
    //Returns true if the node full
    ///////////////////////////////
    boolean isFull() {
        if (this.Size == 2 * T - 1)
            return true;
        else
            return false;
    }
}
