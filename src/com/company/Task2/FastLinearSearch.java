package com.company.Task2;

import java.util.Arrays;

/**
 * Created by ALEKSANDR NECHEUKHIN on 04.12.2016.
 */
public class FastLinearSearch<Type> {
    private Type[] Data;

    /////////////
    //Constructor
    /////////////
    public FastLinearSearch(final Type[] data) {
        Data = (Type[]) new Object[data.length + 1];
        Data = Arrays.copyOf(data, data.length + 1);
    }

    ///////////////////////////////////////////////////////
    //Returns the index of the element that has been found
    // or emit exception
    //////////////////////////////////////////////////////
    public int search(Type pattern) throws Exception {
        Data[Data.length - 1] = pattern;
        int index = 0;
        while (true) {
            if (pattern.equals(Data[index++]))
                break;
        }
        //Check if the cycle was finished in the end
        if (index == Data.length)
            throw new Exception("Couldn't find the element");
        else
            return --index;
    }
}
