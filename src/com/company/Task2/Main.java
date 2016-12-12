package com.company.Task2;


import java.util.Arrays;
import java.util.Random;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/6/2016.
 */
public class Main {

    public static void main(String[] argc) throws Exception{
        //Generate Data
        String pattern = getWordToFind();
        String[] strings = generateDomainInformation(pattern, 10000);

        //Sorted copy
        String[] sortedStrings = strings.clone();
        Arrays.sort(sortedStrings);

        Arrays.sort(strings);
        LinearSearch<String> linearSearch = new LinearSearch<>(strings);
        FastLinearSearch<String> fastLinearSearch = new FastLinearSearch<>(strings);
        SortedLinearSearch<String> sortedLinearSearch = new SortedLinearSearch<>(sortedStrings);
        BinarySearch<String> binarySearch = new BinarySearch<>(sortedStrings);
        BlockSearch<String> blockSearch = new BlockSearch<String>(sortedStrings);
        /*
        //Try to search using linear algorithm
        try {
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

        String text = stringFromArray(strings);
        System.out.println("TEXT: " + text);
        System.out.println("PATTERN: " + pattern);

        //Run cycle to find out approximate time
        BoyerMoore boyerMoore = new BoyerMoore(text);
        try {
            System.out.println(boyerMoore.search(pattern));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        Thread.sleep((long)2);
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