package com.company.Task2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ALEKSANDR NECHEUKHIN on 04.12.2016.
 */
public class BoyerMoore {
    String Data;
    //Table stopSymbols:
    //  'a' -   5;
    //  'b' -   2;
    //  'c' -   7;
    //Integer - last character position in pattern (except last symbol)
    Map<Character, Integer> tableStopSymbols;

    /////////////
    //Constructor
    /////////////
    BoyerMoore(String text) {
        this.Data = text;
    }

    ///////////////////////////
    //Searching pattern in text
    ///////////////////////////
    public int search(String pattern) throws Exception {

        if(pattern.length() > this.Data.length())
            throw new Exception("Couldn't find the pattern");

        fillTableStopSymbols(pattern);
        int indexFinishSearch = this.Data.length()-pattern.length();
        int indexFinishPattern = pattern.length()-1;
        //Cycle by text
        for (int i = 0; i <= indexFinishSearch ; ) {
            for(int j=indexFinishPattern; j >= 0; j--) {
                //There's no Data symbol in pattern
                if(this.Data.charAt(i+j) != pattern.charAt(j))
                {
                    //How much must we move the pattern
                    if(tableStopSymbols.containsKey(this.Data.charAt(i+j)))
                        i += tableStopSymbols.get(this.Data.charAt(i+j));
                    else
                        i += pattern.length()-1;
                    break;
                }
                //Return index when we've found the pattern
                if(j == 0)
                    return i;
            }
        }
        throw new Exception("Couldn't find the pattern");
    }

    /////////////////////////////
    //Fill the stop symbols table
    /////////////////////////////
    private void fillTableStopSymbols(String pattern) {
        tableStopSymbols = new HashMap<>();
        //negative 1 is used cause indexation from 0
        //and algorithm doesn't use the last pattern symbol
        for (int i = 1; i < pattern.length(); i++) {
            tableStopSymbols.put(pattern.charAt(i-1), pattern.length()-i);
        }
        //Add last char in pattern
        tableStopSymbols.put(pattern.charAt(pattern.length()-1), pattern.length()-1);
    }
}
