package com.company.Task5;

/**
 * Created by ALEKSANDR NECHEUKHIN on 24.12.2016.
 */
public class Main {
    public static void main(String[] argv) throws Exception {
        Hash1 hash3 = new Hash1(7);
        hash3.add(new test("Привет"));
        hash3.add(new test("привет"));
        hash3.add(new test("Привес"));
        hash3.add(new test("приcеy"));
        hash3.add(new test("приnеy"));
        hash3.add(new test("приВеy"));
        hash3.add(new test("прИвеy"));

    }
}
