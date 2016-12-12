package com.company.Task2;

/**
 * Created by ALEKSANDR NECHEUKHIN on 04.12.2016.
 */
public class LinearSearch<Type> {
    private Type[] Data;
    /////////////
    //Constructor
    /////////////
    public LinearSearch(final Type[] data) {
        Data = data;
    }

    //////////////////////////////////////////////////////
    //Returns the index of the element that has been found
    // or emit exception
    //////////////////////////////////////////////////////
    public int search(Type pattern) throws Exception {
        int index = 0;
        while (true) {
            if (this.Data.length == index)
                throw new Exception("Couldn't find the element");
            if (pattern.equals(Data[index++]))
                return --index;
        }
    }
}