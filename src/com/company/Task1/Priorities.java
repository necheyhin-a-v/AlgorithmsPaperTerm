package com.company.Task1;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/4/2016.
 */

public enum Priorities
{
    HIGHEST(3),
    MIDDLE(2),
    LOWEST(1);
    private Priorities(int value)
    {
        this.Value = value;
    }
    public static boolean hasHighestPriority(Priorities first, Priorities second)
    {
        if(first.Value > second.Value)
            return true;
        else
            return false;
    }
    private int Value;
}
