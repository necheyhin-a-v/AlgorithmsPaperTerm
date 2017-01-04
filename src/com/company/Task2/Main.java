package com.company.Task2;


import com.company.Task3.ShellSort;
import com.company.Task3.SortType;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/6/2016.
 */
public class Main {

    public static void main(String[] argc) throws Exception{
        //Generate Data
        String pattern = getWordToFind();
        String[] strings = generateDomainInformation(pattern, 9000);

        //Sorted copy
        String[] sortedStrings = strings.clone();

        ShellSort shellSort = new ShellSort(strings.clone(), SortType.DESCENDING);
        String[] descendingSortedStrings = (String[]) shellSort.sort();

        Arrays.sort(sortedStrings);

        LinearSearch<String> linearSearch = new LinearSearch<>(strings);
        SortedLinearSearch<String> sortedLinearSearch = new SortedLinearSearch<>(sortedStrings);
        FastLinearSearch<String> fastLinearSearch = new FastLinearSearch<>(strings);
        BinarySearch<String> binarySearch = new BinarySearch<>(sortedStrings);
        BlockSearch<String> blockSearch = new BlockSearch<>(sortedStrings);

        long startLinear = System.nanoTime();
        //Try to search using linear algorithm
        try {
            System.out.println("Position: using linear search algorithm: " + linearSearch.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endLinear = System.nanoTime();


        long startSortedLinear = System.nanoTime();
        //Try to search using fast sorted linear algorithm
        try {
            System.out.println("Position: using sorted linear search algorithm: " + sortedLinearSearch.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endSortedLinear = System.nanoTime();

        long startFastLinear = System.nanoTime();
        //Try to search using fast sorted linear algorithm
        try {
            System.out.println("Position: using fast linear search algorithm: " + fastLinearSearch.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endFastLinear = System.nanoTime();


        long startBinary = System.nanoTime();
        //Try to search using binary algorithm
        try {
            System.out.println("Position: using binary search algorithm: " + binarySearch.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endBinary = System.nanoTime();


        long startBlock = System.nanoTime();
        //Try to search using block algorithm
        try {
            System.out.println("Position: using block algorithm: " + blockSearch.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endBlock = System.nanoTime();


        String sortedText = stringFromArray(strings);
        String descendingSortedText = stringFromArray(descendingSortedStrings);
        String textRaw = stringFromArray(strings);
        long startBoyerMooreRawData = System.nanoTime();
        //Run cycle to find out approximate time
        BoyerMoore boyerMoore = new BoyerMoore(textRaw);
        try {
            System.out.println(boyerMoore.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endBoyerMooreRawData = System.nanoTime();


        long startBoyerMooreSorted = System.nanoTime();
        //Run cycle to find out approximate time
        boyerMoore = new BoyerMoore(sortedText);
        try {
            System.out.println(boyerMoore.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endBoyerMooreSorted = System.nanoTime();


        long startBoyerMooreDescSorted = System.nanoTime();
        //Run cycle to find out approximate time
        boyerMoore = new BoyerMoore(descendingSortedText);
        try {
            System.out.println(boyerMoore.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endBoyerMooreDescSorted = System.nanoTime();

        System.out.println("Time BoyerMoore raw data: " + (endBoyerMooreRawData - startBoyerMooreRawData));
        System.out.println("Time BoyerMoore sorted data: " + (endBoyerMooreSorted - startBoyerMooreSorted));
        System.out.println("Time BoyerMoore descending sorted data: " + (endBoyerMooreDescSorted - startBoyerMooreDescSorted));
        System.out.println("Time Linear: " + (endLinear - startLinear));
        System.out.println("Time SortedLinear: " + (endSortedLinear - startSortedLinear));
        System.out.println("Time FastLinear: " + (endFastLinear - startFastLinear));
        System.out.println("Time Binary: " + (endBinary - startBinary));
        System.out.println("Time Block: " + (endBlock - startBlock));
    }
    //////////////////////////////////////////////////////////////////////////
    //Generates a sequence with only one occurrence in expression "wordToFind"
    /////////////////////////////////////////////////////////////////////////
    private static String[] generateDomainInformation(String wordToFind, int countElements) throws Exception{
        //Generate random pattern position
        Random random = new Random(System.currentTimeMillis());
        int patternPosition = random.nextInt(countElements-1);
        //To form result array
        String [] result = new String[countElements];
        for(int i=0; i<countElements; i++) {
            if(i==patternPosition) {
                result[i] = wordToFind;
                continue;
            }
            String currentWord = getWordToFind();
            if (currentWord.compareTo(wordToFind) == 0) {
                i--;
                continue;
            }
            result[i] = currentWord;
        }
        return result;
    }
    ///////////////////////////////////////////
    //Method selects the random word from array
    ///////////////////////////////////////////
    private static String getWordToFind() throws Exception{
        String[] arrayDomainInf = {"адрес", "почтовый", "отделение", "адресат","отправитель", "индекс", "накладная", "абонент",
                "возврат", "знак", "марка", "оплата", "входящий", "исходящий", "служба", "сторона", "вес", "пакет", "письмо", "посылка",
                "штемпель", "класс", "вручение", "секограмма"};
        Random random = new Random(System.currentTimeMillis());
        Thread.sleep(1);
        int index = random.nextInt(arrayDomainInf.length-1);
        return arrayDomainInf[index];
    }
    /////////////////////////////
    //Generates string from array
    /////////////////////////////
    private static String stringFromArray(String[] array)
    {
        String result = "";
        for(int i=0; i<array.length; i++) {
            result += array[i] + " ";
        }
        return result;
    }
}