package com.company.Task2;


import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/6/2016.
 */
public class Main {

    public static void main(String[] argc) {
        //Generate Data
        String[] strings = {"1", "3", "2", "5", "8", "4", "7", "15", "81", "81", "82"};
        //Sorted copy
        String[] sortedStrings = strings.clone();
        Arrays.sort(sortedStrings);

        Arrays.sort(strings);
        LinearSearch<String> linearSearch = new LinearSearch<>(strings);
        FastLinearSearch<String> fastLinearSearch = new FastLinearSearch<>(strings);
        SortedLinearSearch<String> sortedLinearSearch = new SortedLinearSearch<>(sortedStrings);
        BinarySearch<String> binarySearch = new BinarySearch<>(sortedStrings);
        BlockSearch<String> blockSearch = new BlockSearch<String>(sortedStrings);
        //Try to search using linear algorithm
        /*try {
            System.out.println(linearSearch.search("1"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Try to search using linear algorithm
        try {
            System.out.println(linearSearch.search("5"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Try to search using fast sorted linear algorithm
        try {
            System.out.println(sortedLinearSearch.search("8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Try to search using binary algorithm
        try {
            System.out.println(binarySearch.search("0"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Try to search using block algorithm
        try {
            System.out.println(blockSearch.search("9"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        BoyerMoore boyerMoore = new BoyerMoore("привет как дела");
        try {
            System.out.println(boyerMoore.search("как"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
