package com.company.Task2;

/**
 * Created by ALEKSANDR NECHEUKHIN on 04.12.2016.
 */
public class BlockSearch<Type> {
    Comparable<Type>[] Data;
    int SizeBlock;

    /////////////
    //Constructor
    /////////////
    BlockSearch(Comparable<Type>[] data) {
        this.SizeBlock = 10;
        this.Data = data;
    }

    /////////////
    //Constructor
    /////////////
    BlockSearch(Comparable<Type>[] data, int sizeBlock) {
        this.SizeBlock = sizeBlock;
        this.Data = data;
    }


    public int search(Type pattern) throws Exception {
        int startBlock = 0;
        while (true) {
            int endBlock = startBlock + this.SizeBlock;
            if (startBlock + this.SizeBlock >= this.Data.length)
                return searchInBlock(pattern, startBlock, this.Data.length-1);
            if (this.Data[endBlock].compareTo(pattern) >= 0)
                return searchInBlock(pattern, startBlock, endBlock);
            startBlock += this.SizeBlock;
        }
    }


    private int searchInBlock(Type pattern, int start, int end) throws Exception {
        for (int i = start; i < end; i++)
            if (pattern.equals(Data[i]))
                return i;
        throw new Exception("Couldn't find the element");
    }
}
