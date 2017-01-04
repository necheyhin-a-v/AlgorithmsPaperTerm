package com.company.Task3;

import java.util.Random;

/**
 * Created by ALEKSANDR NECHEUKHIN on 12.12.2016.
 */
public class Main {

    public static void main(String[] argc) throws Exception {
        Double[] RawArray = generateDomainInformation(60000);


        /*System.out.println("RAW data: ");
        for (Double current : RawArray) {
            System.out.format(String.format("%.2f ", current));
        }*/
        System.out.println();

        long timeStartInsertion = System.currentTimeMillis();
        InsertionSort insertionSort = new InsertionSort(RawArray, SortType.ASCENDING);
        Object[] sorted = insertionSort.sort();
        /*System.out.println("Sorted with insertionSort data: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndInsertion = System.currentTimeMillis();

        long timeStartInsertionSorted = System.currentTimeMillis();
        insertionSort = new InsertionSort((Double[]) sorted, SortType.ASCENDING);
        sorted = insertionSort.sort();
        /*System.out.println("Sorted with insertionSort data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndInsertionSorted = System.currentTimeMillis();

        long timeStartInsertionSortedBack = System.currentTimeMillis();
        insertionSort = new InsertionSort((Double[]) sorted, SortType.DESCENDING);
        sorted = insertionSort.sort();
        /*System.out.println("Sorted back with insertionSort data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndInsertionSortedBack = System.currentTimeMillis();

        long timeStartShell = System.currentTimeMillis();
        ShellSort shellSort = new ShellSort(RawArray, SortType.ASCENDING);
        sorted = shellSort.sort();
        /*System.out.println("Sorted with SHELL data: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndShell = System.currentTimeMillis();

        long timeStartShellSorted = System.currentTimeMillis();
        shellSort = new ShellSort((Double[]) sorted, SortType.ASCENDING);
        sorted = shellSort.sort();
        /*System.out.println("Sorted with SHELL data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }*/
        System.out.println();
        long timeEndShellSorted = System.currentTimeMillis();

        long timeStartShellSortedBack = System.currentTimeMillis();
        shellSort = new ShellSort((Double[]) sorted, SortType.DESCENDING);
        sorted = shellSort.sort();
        /*System.out.println("Sorted back with SHELL data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndShellSortedBack = System.currentTimeMillis();

        long timeStartQuick = System.currentTimeMillis();
        QuickSort quickSort = new QuickSort(RawArray, SortType.ASCENDING);
        sorted = quickSort.sort();
        /*System.out.println("Sorted with QUICK data: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndQuick = System.currentTimeMillis();

        long timeStartQuickSorted = System.currentTimeMillis();
        quickSort = new QuickSort((Double[]) sorted, SortType.ASCENDING);
        sorted = quickSort.sort();
        /*System.out.println("Sorted with QUICK data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndQuickSorted = System.currentTimeMillis();

        long timeStartQuickSortedBack = System.currentTimeMillis();
        quickSort = new QuickSort((Double[]) sorted, SortType.DESCENDING);
        sorted = quickSort.sort();
        /*System.out.println("Sorted back with SHELL data that has been sorted: ");
        for (Object current : sorted) {
            System.out.print(String.format("%.2f ", current));
        }
        System.out.println();*/
        long timeEndQuickSortedBack = System.currentTimeMillis();

        System.out.println("TIME INSERTION sorted raw: " + (timeEndInsertion - timeStartInsertion));
        System.out.println("TIME INSERTION sorted data that have been sorted: " + (timeEndInsertionSorted - timeStartInsertionSorted));
        System.out.println("TIME INSERTION sorted back: " + (timeEndInsertionSortedBack - timeStartInsertionSortedBack));

        System.out.println("TIME SHELL sorted raw: " + (timeEndShell - timeStartShell));
        System.out.println("TIME SHELL sorted data that have been sorted: " + (timeEndShellSorted - timeStartShellSorted));
        System.out.println("TIME SHELL sorted back: " + (timeEndShellSortedBack - timeStartShellSortedBack));

        System.out.println("TIME QUICK sorted raw: " + (timeEndQuick - timeStartQuick));
        System.out.println("TIME QUICK sorted data that have been sorted: " + (timeEndQuickSorted - timeStartQuickSorted));
        System.out.println("TIME QUICK sorted back: " + (timeEndQuickSortedBack - timeStartQuickSortedBack));


    }

    //////////////////////////////////////////////////////////////////////////
    //Generates a sequence with only one occurrence in expression "wordToFind"
    /////////////////////////////////////////////////////////////////////////
    private static Double[] generateDomainInformation(int countElements) throws Exception {
        //Generate random
        Random random = new Random(System.currentTimeMillis());
        //To form result array
        Double[] result = new Double[countElements];
        for (int i = 0; i < countElements; i++) {
            Thread.sleep(1);
            result[i] = 0 + random.nextDouble() * (10000 - 0 + 1);    //min + rand*(max-min+1)
        }
        return result;
    }
}
