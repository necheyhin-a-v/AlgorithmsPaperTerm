package com.company.Task3;

/**
 * Created by ALEKSANDR NECHEUKHIN on 15.12.2016.
 */
public class QuickSort<Type> {
    Comparable<Type>[] Data;
    SortType SortType;

    QuickSort(final Comparable<Type>[] array, SortType type) {
        this.SortType = type;
        this.Data = array;
    }

    public Type[] sort() throws Exception {
        if (SortType == SortType.ASCENDING)
            ascendingSort(0, this.Data.length - 1);
        else
            descendingSort(0, this.Data.length - 1);
        return (Type[]) Data;
    }

    ///////////////////////
    //From low to high sort
    ///////////////////////
    private void ascendingSort(int left, int right) throws Exception {
        if (this.Data.length == 0)
            throw new Exception("The array has 0 length");
        if (left < right) {
            //TODO: невозможно вычислять медиану, т.к. для ее вычисления необходима сортировка. Можно использовать среднее арифметическое
            Type middle = (Type) this.Data[(left + right) / 2];
            int i = left;
            int j = right;
            while (i <= j) {
                while (this.Data[i].compareTo(middle) < 0) i++;
                while (this.Data[j].compareTo(middle) > 0) j--;
                //Swap elements
                if (i <= j) {
                    Comparable<Type> temp = this.Data[i];
                    this.Data[i] = this.Data[j];
                    this.Data[j] = temp;
                    i++;
                    j--;
                }
            }
            //Call recursive if there is something to sort
            if (j > 0) ascendingSort(left, j);
            if (right > i) ascendingSort(j + 1, right);
        }
    }

    ///////////////////////
    //From high to low sort
    ///////////////////////
    private void descendingSort(int left, int right) throws Exception {
        if (this.Data.length == 0)
            throw new Exception("The array has 0 length");
        if (left < right) {
            //TODO: невозможно вычислять медиану, т.к. для ее вычисления необходима сортировка. Можно использовать среднее арифметическое
            Type middle = (Type) this.Data[(left + right) / 2];
            int i = left;
            int j = right;
            while (i <= j) {
                while (this.Data[i].compareTo(middle) > 0) i++;
                while (this.Data[j].compareTo(middle) < 0) j--;
                //Swap elements
                if (i <= j) {
                    Comparable<Type> temp = this.Data[i];
                    this.Data[i] = this.Data[j];
                    this.Data[j] = temp;
                    i++;
                    j--;
                }
            }
            //Call recursive if there is something to sort
            if (j > 0) descendingSort(left, j);
            if (right > i) descendingSort(j + 1, right);
        }
    }
}
