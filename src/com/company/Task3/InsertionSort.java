package com.company.Task3;

import java.util.Arrays;

/**
 * Created by ALEKSANDR NECHEUKHIN on 15.12.2016.
 */
public class InsertionSort<Type> {

    private Comparable<Type>[] Data;
    private SortType SortType;

    public InsertionSort(Comparable<Type>[] array, SortType sortType) {
        Data = Arrays.copyOf(array, array.length);
        SortType = sortType;
    }

    public Type[] sort() throws Exception {
        if (SortType == SortType.ASCENDING)
            return ascendingSort();
        else
            return descendingSort();
    }

    ///////////////////////
    //From low to high sort
    ///////////////////////
    private Type[] ascendingSort() throws Exception {
        if (this.Data.length == 0)
            throw new Exception("The array has 0 length");
        int j;
        for (int i = 0; i < Data.length; i++) {
            Type elementToSort = (Type) Data[i];
            //Search place in sorted part
            for (j = i - 1; j >= 0 && (Data[j].compareTo(elementToSort) > 0); j--) {
                Data[j + 1] = Data[j];    //Move current element to right while place isn't right
            }
            //The right place has been found
            Data[j + 1] = (Comparable<Type>) elementToSort;
        }
        return (Type[]) Data;
    }

    ///////////////////////
    //From high to low sort
    ///////////////////////
    private Type[] descendingSort() throws Exception {
        if (this.Data.length == 0)
            throw new Exception("The array has 0 length");
        int j;
        for (int i = 0; i < Data.length; i++) {
            Type elementToSort = (Type) Data[i];
            //Search place in sorted part
            for (j = i - 1; j >= 0 && (Data[j].compareTo(elementToSort) < 0); j--) {
                Data[j + 1] = Data[j];    //Move current element to right while place isn't right
            }
            //The right place has been found
            Data[j + 1] = (Comparable<Type>) elementToSort;
        }
        return (Type[]) Data;
    }
}
