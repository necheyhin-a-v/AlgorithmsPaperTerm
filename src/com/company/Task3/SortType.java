package com.company.Task3;

/**
 * Created by ALEKSANDR NECHEUKHIN on 15.12.2016.
 */
public enum SortType {
    ASCENDING(0),   //FROM LOW TO HIGH
    DESCENDING(1);  //FROM HIGH TO LOW

    SortType(int value) {
        this.Value = value;
    }

    private int Value;
}