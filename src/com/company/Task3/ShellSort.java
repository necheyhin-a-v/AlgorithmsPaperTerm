package com.company.Task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEKSANDR NECHEUKHIN on 15.12.2016.
 */
public class ShellSort<Type> {
    private Comparable<Type>[] Data;
    private SortType SortType;
    private List<Integer> Incrementation;

    //Using default Sedjvik's series
    ShellSort(final Comparable<Type>[] array, SortType type) {
        this.Data = array;
        this.SortType = type;
        Incrementation = new ArrayList<>();
        calculateSeries();
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
        int countCycles = Incrementation.size() - 1;
        while (countCycles >= 0) {
            int dividePart = Incrementation.get(countCycles--);
            //Using Insertion sort
            int j;
            for (int i = dividePart; i < Data.length; i++) {
                Type elementToSort = (Type) Data[i];
                //Search place in sorted part
                for (j = i - 1; j >= 0 && (Data[j].compareTo(elementToSort) > 0); j--) {
                    Data[j + 1] = Data[j];    //Move current element to right while place isn't right
                }
                //The right place has been found
                Data[j + 1] = (Comparable<Type>) elementToSort;
            }
        }
        return (Type[]) Data;
    }

    ///////////////////////
    //From high to low sort
    ///////////////////////
    private Type[] descendingSort() throws Exception {
        if (this.Data.length == 0)
            throw new Exception("The array has 0 length");
        int countCycles = Incrementation.size() - 1;
        while (countCycles >= 0) {
            int dividePart = Incrementation.get(countCycles--);
            //Using Insertion sort
            int j;
            for (int i = dividePart; i < Data.length; i++) {
                Type elementToSort = (Type) Data[i];
                //Search place in sorted part
                for (j = i - 1; j >= 0 && (Data[j].compareTo(elementToSort) < 0); j--) {
                    Data[j + 1] = Data[j];    //Move current element to right while place isn't right
                }
                //The right place has been found
                Data[j + 1] = (Comparable<Type>) elementToSort;
            }
        }
        return (Type[]) Data;
    }


    /////////////////////////////
    //Calculate series by Sedjvik
    /////////////////////////////
    private void calculateSeries() {
        Incrementation.add(1);
        int i = 1;
        do {
            if (i % 2 == 0)
                Incrementation.add((int) (9 * Math.pow(2, i) - 9 * Math.pow(2, i / 2)));
            else
                Incrementation.add((int) (8 * Math.pow(2, i) - 6 * Math.pow(2, (i + 1) / 2) + 1));
            i++;

        } while (Incrementation.get(i - 1) < this.Data.length && 3 * Incrementation.get(i - 1) < Data.length);
    }
}
