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
    BTree(int t) throws Exception {
        if (t < 2)
            throw new Exception("Degree must be more than 1");
        this.T = t;
        this.Root = new BTreeNode(t);
    }

    ////////////////////////////////////////////////
    //Delete element value from the tree if possible
    ////////////////////////////////////////////////
    public void delete(Comparable<Type> value) {
        while (contains(value)) {
            delete(value, this.Root);
            if (this.Root.Size == 0)
                this.Root = this.Root.Children[0];

        }
    }

    //////////////////////////////////////////////
    //Recursive deletion with rearranging the tree
    ///////////////////////////////////////////////
    private void delete(Comparable<Type> value, BTreeNode parent) {

        ///Check all Values in parent
        for (int i = 0; i < parent.Size; i++) {
            //Just remove the value in case it's leaf
            if (parent.Leaf && parent.Values[i].compareTo(value) == 0) {
                //Move all elements to the left
                for (int j = i; j < parent.Size - 1; j++)
                    parent.Values[j] = parent.Values[j + 1];
                parent.Size--;
                //Set removed value as null
                parent.Values[parent.Size] = null;
                return;  //finish
            }
            //parent isn't leaf but the value in parent
            else if (!parent.Leaf && parent.Values[i].compareTo(value) == 0) {
                BTreeNode leftChild = parent.Children[i];
                BTreeNode rightChild = parent.Children[i + 1];
                //If the value from leftChild can be move to the parent
                if (leftChild.Size >= T) {
                    shareValue(parent, i, i + 1);
                    delete(value, rightChild);
                    return;
                }
                //If the Value from rightChild can be moved to the parent
                else if (rightChild.Size >= T) {
                    //Get newValue from rightChild
                    shareValue(parent, i + 1, i);
                    delete(value, leftChild);
                    return;
                }
                //Not good, we have to merge leftChild, rightChild and Value
                //Value will be median
                else {
                    parent = mergeNodes(parent, i);
                    delete(value, parent);
                    return;
                }
            }
        }
        if (!parent.Leaf) {
            ///SEE NEXT LEVEL
            ///Check children first before going deeper inside the tree
            ///Determine the appropriate branch to go further
            int parentIndex = parent.Size - 1;
            int childIndex = parent.Size;
            for (int i = 0; i < parent.Size; i++) {
                if (parent.Values[i].compareTo(value) > 0) {
                    parentIndex = i;
                    childIndex = i;
                    break;  //not increase index, because it could lead to wrong branch
                }
            }
            //Make sure that we can go deeper
            BTreeNode nextTree = parent.Children[childIndex];
            if (nextTree.Size == T - 1) {
                //If the value from leftChild can be move to the parent
                if (childIndex == 0) {
                    if (parent.Children[childIndex + 1].Size >= T)
                        shareValue(parent, childIndex + 1, childIndex);
                    else
                        nextTree = mergeNodes(parent, parentIndex);
                }
                //If the Value from rightChild can be moved to the parent
                else if (childIndex == parent.Size) {
                    if (parent.Children[childIndex - 1].Size >= T)
                        shareValue(parent, childIndex - 1, childIndex);
                    else
                        nextTree = mergeNodes(parent, parentIndex);
                } else if (parent.Children[childIndex - 1].Size >= T || parent.Children[childIndex + 1].Size >= T) {
                    //Get Value from left or from right
                    if (parent.Children[childIndex - 1].Size >= T)
                        shareValue(parent, childIndex - 1, childIndex);
                    else
                        shareValue(parent, childIndex + 1, childIndex);
                }
                //Not good, we have to merge leftChild, rightChild and Value
                //Value will be median
                else {
                    nextTree = mergeNodes(parent, parentIndex);
                }
            }
            //Now we are sure that subtree has enough values to delete
            delete(value, nextTree);
        }
    }

    //////////////////////////////////////////
    //Function to share value between children
    ///////////////////////////////////////////
    public void shareValue(BTreeNode parent, int indexSource, int indexDest) {
        int indexInParent = (indexDest + indexSource) / 2;
        BTreeNode source;
        BTreeNode dest;
        dest = parent.Children[indexDest];
        source = parent.Children[indexSource];
        //Get value from subtree that is located more right
        if (indexSource > indexDest) {
            //Set new value from parent to dest
            dest.Values[dest.Size] = parent.Values[indexInParent];
            //Set value from source to parent
            parent.Values[indexInParent] = source.Values[0];
            //Copy child to the dest
            if (!dest.Leaf) {
                dest.Children[dest.Size + 1] = source.Children[0];
                //Move all children in source to the beginning
                for (int i = 0; i < source.Size; i++)
                    source.Children[i] = source.Children[i + 1];
            }
            //Move all children in the source to the beginning
            for (int i = 0; i < source.Size - 1; i++)
                source.Values[i] = source.Values[i + 1];
            dest.Size++;
            source.Size--;
            //Free old child and value in source
            source.Values[source.Size] = null;
            source.Children[source.Size + 1] = null;
        }
        //Get value from subtree that is located more left
        else {
            //Free one position from the beginning to put there new value
            for (int i = dest.Size; i > 0; i--)
                dest.Values[i] = dest.Values[i - 1];
            //Set new value from parent to dest
            dest.Values[0] = parent.Values[indexInParent];
            //Set value from source to parent
            parent.Values[indexInParent] = source.Values[source.Size - 1];
            //Copy child to the dest
            if (!dest.Leaf) {
                //Free one position from the beginning to put there new child
                for (int i = dest.Size + 1; i > 0; i--)
                    dest.Children[i] = dest.Children[i - 1];
                //Copy child from source
                dest.Children[0] = source.Children[source.Size];
            }
            dest.Size++;
            source.Size--;
            //Free old child and value in source
            source.Children[source.Size + 1] = null;
            source.Values[source.Size] = null;
        }
    }

    ////////////////////////////////////
    //Function merges two children nodes
    ////////////////////////////////////
    public BTreeNode mergeNodes(BTreeNode parent, int indexChildToMerge) {
        int i = indexChildToMerge;
        BTreeNode leftChild = parent.Children[i];
        BTreeNode rightChild = parent.Children[i + 1];
        //Copy parent value to leftChild
        leftChild.Values[leftChild.Size] = parent.Values[i];
        leftChild.Size++;
        //Copy rightChild to leftChild
        for (int j = 0; j < rightChild.Size; j++) {
            leftChild.Values[leftChild.Size + j] = rightChild.Values[j];
        }
        //Copy all rightChild children to leftChild
        for (int j = 0; j <= rightChild.Size; j++) {
            leftChild.Children[leftChild.Size + j] = rightChild.Children[j];
        }
        //Set new leftChildSize
        leftChild.Size += rightChild.Size;

        //clear "value" from parent
        //Put "value" from parent to the layer below
        for (int j = i; j < parent.Size - 1; j++) {
            parent.Values[j] = parent.Values[j + 1];
            parent.Children[j + 1] = parent.Children[j + 2];
        }
        parent.Size--;
        //free value and child from parent that have been removed
        parent.Values[parent.Size] = null;
        parent.Children[parent.Size + 1] = null;
        //Return merged node
        return leftChild;
    }

    ///////////////////////////////////////////
    //Returns true if the tree contains "value"
    ///////////////////////////////////////////
    public boolean contains(Comparable<Type> value) {
        try {
            BTreeNode found = search(value, this.Root);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    ///////////////////////////////////////////
    //Method returns subTree or emit Exception
    //////////////////////////////////////////
    public BTreeNode search(Comparable<Type> value, BTreeNode parent) throws Exception {
        //Check all parent values
        for (int i = 0; i < parent.Size; i++) {
            if (parent.Values[i].compareTo(value) == 0)
                return parent;
        }
        //Determine the appropriate branch to go further
        for (int i = 0; i < parent.Size; i++) {
            if (parent.Values[i].compareTo(value) > 0 && !parent.Leaf)
                return search(value, parent.Children[i]);
        }
        //Try to find value in the last branch if possible
        if (!parent.Leaf)
            return search(value, parent.Children[parent.Size]);
        throw new Exception("Couldn't find value");
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
        rightChild.Leaf = leftChild.Leaf;       //Set the same status as splitting node(it can be leaf or not)
        rightChild.Size = leftChild.Size / 2;   //WARNING not to use binary tree

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

        Queue<BTreeNode> queue = new LinkedList<>();    //queue to add
        int currentCount = 1;       //Count elements on current layer
        int nextCount = 0;          //Count elements on the next layer
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
