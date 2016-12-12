package com.company.Task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/4/2016.
 */
//class Stack
public class MyStack <Type> {
    private MyNode<Type> Top;               //Top pointer on TOP
    private int Count;                      //Number of stack elements

    /////////////
    //Constructor
    /////////////
    public MyStack()
    {
        this.Count = 0;
        this.Top = null;
    }
    //////////////////////
    //Add element to stack
    //////////////////////
    public void push(Type data)
    {
        MyNode<Type> element = new MyNode<Type>();
        if(this.Count == 0)
            element.PreviousElement = null;
        else
            element.PreviousElement = this.Top;
        element.Data = data;
        this.Top = element;
        this.Count++;
    }
    ///////////////////////////
    //Remove element from stack
    ///////////////////////////
    public Type pop() throws Exception
    {
        if(this.Count == 0)
            throw new Exception("Can't pop element. Stack is empty");
        MyNode<Type> element = new MyNode<Type>();
        element = this.Top;
        this.Top = this.Top.PreviousElement;
        this.Count--;
        return element.Data;
    }
    /////////////////////////////////////////
    //Get element from stack without removing
    /////////////////////////////////////////
    public Type peek() throws Exception
    {
        if(this.Count == 0)
            throw new Exception("Can't peak element. Stack is empty");
        return this.Top.Data;
    }
    //////////////////////
    //Check is stack empty
    //////////////////////
    public boolean isEmpty()
    {
        if(this.Count == 0)
            return true;
        else
            return false;
    }
    //Return count stack elements
    public int count()
    {
        return this.Count;
    }
    ////////////////////
    //Get stack elements
    ////////////////////
    public List<Type> getStackElements()
    {
        ArrayList<Type> list = new ArrayList<>();
        //Create copy of top
        MyNode<Type> top = new MyNode<>();
        top.PreviousElement = this.Top.PreviousElement;
        top.Data = this.Top.Data;
        Type element;
        while ((element = getElement(top)) != null) {
            list.add(element);
            top = top.PreviousElement;
        }
        return list;
    }
    /////////////////////////////
    //Get data from "TOP" element
    /////////////////////////////
    private Type getElement(MyNode<Type> top)
    {
        MyNode<Type> element;
        element = top;
        if(top == null)
            return null;
        else
            return element.Data;
    }

}