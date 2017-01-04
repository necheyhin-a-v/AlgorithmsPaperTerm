package com.company.Task5;

import java.io.FileWriter;
import java.util.Random;

/**
 * Created by ALEKSANDR NECHEUKHIN on 24.12.2016.
 */
public class Main {
    public static void main(String[] argv) throws Exception {

        Double[] array = generateDomainInformation(1000);
        Hash1 hash1 = new Hash1(array.length);
        Hash2 hash2 = new Hash2(array.length);
        Hash3 hash3 = new Hash3(array.length);
        for (int i = 0; i < array.length; i++) {
            hash1.add(array[i]);
            hash2.add(array[i]);
            hash3.add(array[i]);
        }
        //Create output file
        FileWriter file = new FileWriter("D:\\task5.csv");


        Integer[] collisions1 = hash1.getCollisions();
        Integer[] collisions2 = hash2.getCollisions();
        Integer[] collisions3 = hash3.getCollisions();

        for (int i = 0; i < array.length; i++) {
            file.write("Hash1;" + i + ";" + collisions1[i] + "\n");
            file.write("Hash2;" + i + ";" + collisions2[i] + "\n");
            file.write("Hash3;" + i + ";" + collisions3[i] + "\n");

        }
        System.out.println("Count collision for hash1: " + hash1.getCollisionCount());
        System.out.println("Count collision for hash2: " + hash2.getCollisionCount());
        System.out.println("Count collision for hash3: " + hash3.getCollisionCount());

        file.close();
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
