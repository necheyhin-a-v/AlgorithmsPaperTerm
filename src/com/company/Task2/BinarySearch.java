package com.company.Task2;

/**
 * Created by ALEKSANDR NECHEUKHIN on 04.12.2016.
 */
public class BinarySearch<Type> {
    Comparable<Type>[] Data;
    Type SearchPattern;

    /////////////
    //Constructor
    /////////////
    BinarySearch(Comparable<Type>[] data) {
        this.Data = data;
    }

    ///////////////////////////
    //Wrapper for binary search
    ///////////////////////////
    public int search(Type pattern) throws Exception {
        this.SearchPattern = pattern;
        return searchProcess(0, this.Data.length - 1);
    }

    /////////////////////////
    //Binary search algorithm
    /////////////////////////
    private int searchProcess(int start, int end) throws Exception {
        if (start == end && !this.Data[start].equals(this.SearchPattern))
            throw new Exception("Couldn't find the element");
        int middleIndex = (start + end) / 2;
        //Split array
        if (this.Data[middleIndex].compareTo(this.SearchPattern) > 0)
            return searchProcess(start, middleIndex);
        else if (this.Data[middleIndex].compareTo(this.SearchPattern) < 0)
            return searchProcess(middleIndex + 1, end);
        else
            return middleIndex;
    }
}
