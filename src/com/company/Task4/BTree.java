package com.company.Task4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BTREE CLASS
 */
class BTree<Type> {
    private BTreeNode Root;
    private Integer T;

    /////////////////////////////////////////////
    //Constructor
    //parameter "t" is the minimum degree of tree
    //t=1 - binary TREE
    //t=2 - 2-3 TREE
    //etc.
    /////////////////////////////////////////////
    BTree(int t) {
        this.Root = new BTreeNode(t);
        this.T = t;
    }

    ////////////////////////////
    //Add new value to the tree
    ///////////////////////////
    public void add(Comparable<Type> value) {
        //if we have to create new root
        if (Root.isFull()) {
            BTreeNode newRoot = new BTreeNode(T);
            //Set the old root as a child newRoot
            newRoot.Children[0] = this.Root;
            newRoot.Leaf = false;
            //Set newRoot as a root
            this.Root = newRoot;
            //The old root is full, so we have to split it
            splitChildNode(this.Root, 0);
        }
        add(value, this.Root);
    }

    //////////////////////////////////////
    //Private method to use recursive call
    //////////////////////////////////////
    private void add(Comparable<Type> value, BTreeNode node) {
        int i = node.Size - 1;  //Insertion position
        if (node.Leaf) {
            //Find the place to insert the value into leaf
            while (i >= 0 && value.compareTo((Type) node.Values[i]) < 0) {
                node.Values[i + 1] = node.Values[i];
                i--;
            }
            //Do insertion
            node.Values[i + 1] = value;
            node.Size++;
            //TODO: disk write "node"
        } else {
            //Find the right subTree to insert the value into
            while (i >= 0 && value.compareTo((Type) node.Values[i]) < 0)
                i--;
            i++;
            //TODO: disk read "child" instead of next string
            BTreeNode child = node.Children[i];
            //Split child if need
            if (child.isFull()) {
                splitChildNode(node, i);
                //Correct insertion index after split because method "split" adds one element to
                //parent node from child
                if (value.compareTo((Type) node.Values[i]) > 0)
                    i++;
                child = node.Children[i];
            }
            //recursive call to insert value into the child
            add(value, child);
        }
    }

    //////////////////////////////////////////////////////
    //Split child node with index i and update parent node
    //////////////////////////////////////////////////////
    private void splitChildNode(BTreeNode parent, int childIndexToSplit) {
        int i = childIndexToSplit;              //index which child should be split
        //Create children
        BTreeNode rightChild = new BTreeNode(T);
        BTreeNode leftChild = parent.Children[i];

        //Customize rightChild
        rightChild.Leaf = leftChild.Leaf;   //Set the same status as splitting node(it can be leaf or not)
        rightChild.Size = leftChild.Size / 2; //TODO: check division (T-1) replaced to leftChild.size/2

        //Resize leftChild
        leftChild.Size = rightChild.Size;

        //Move splitting value to parent
        //Some elements can already exist in parent
        for (int j = parent.Size; j > i; j--)
            parent.Values[j] = parent.Values[j - 1];
        parent.Values[i] = leftChild.Values[leftChild.Size];
        leftChild.Values[leftChild.Size] = null;                //remove the value that has been moved

        //Fill right node with values
        for (int j = 0; j < rightChild.Size; j++) {
            rightChild.Values[j] = leftChild.Values[leftChild.Size + 1 + j];
            //Set null all values from the leftChild that have been moved
            leftChild.Values[leftChild.Size + 1 + j] = null;
        }

        //Copy if need all children
        if (!leftChild.Leaf) {
            for (int j = 0; j <= rightChild.Size; j++) {
                rightChild.Children[j] = leftChild.Children[leftChild.Size + 1 + j];
                //Set null all children that have been moved
                leftChild.Children[leftChild.Size + 1 + j] = null;
            }
        }

        //Set customized rightChild to parent
        for (int j = parent.Size; j > i; j--)
            parent.Children[j + 1] = parent.Children[j];
        parent.Children[i + 1] = rightChild;

        //increment parent size
        parent.Size++;
        //TODO: disk write "leftChild", "rightChild", "parent"
    }

    ////////////////
    //Print the tree
    /////////////////
    public void printByLayer() {

        Queue<BTreeNode> queue = new LinkedList<>();
        int currentCount = 1;
        int nextCount = 0;
        queue.add(this.Root);
        while (!queue.isEmpty()) {
            BTreeNode currentNode = queue.poll();

            //print node
            for (int i = 0; i < currentNode.Size; i++)
                System.out.print(currentNode.Values[i] + " ");
            System.out.print("    ");
            currentCount--;

            //Add to the queue all children
            for (int i = 0; i <= currentNode.Size; i++) {
                if (currentNode.Children[i] != null) {
                    queue.add(currentNode.Children[i]);
                    nextCount++;
                }
            }

            //Next layer?
            if (currentCount == 0) {
                System.out.println();
                currentCount = nextCount;
                nextCount = 0;
            }

        }
    }

}
