package com.company.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by ALEKSANDR NECHEUKHIN on 11/4/2016.
 */


public class MyQueue <Type>{
    private int Size;
    private Type [] Data;
    private int IndexStart;      //The first element to be dequeued (its index)
    //////////////
    //Constructor
    //////////////
    public MyQueue()
    {
        resetQueue();
    }
    ////////////////////////////////////
    //Set all elements in initial state
    ///////////////////////////////////
    private void resetQueue()
    {
        this.Size = 0;
        this.Data = (Type[]) new Object[0];
        this.IndexStart = 0;
    }
    //////////////////////////
    //Add element to the queue
    //////////////////////////
    public void enqueue(Type element) throws Exception
    {
        if(this.isEmpty())
            resetQueue();
        if(this.Data.length == this.Size)                           //Not enough memory
        {
            if(this.IndexStart != 0)                                //Uncycle queue
            {
                Type [] temp = (Type[]) new Object[this.Size*2];    //Add some memory
                int indexTemp = 0;

                for(int i=this.IndexStart; i<this.Data.length; i++) //Copy start queue
                    temp[indexTemp++] = this.Data[i];
                for(int i=0; i<this.IndexStart; i++)                //Copy end queue
                    temp[indexTemp++] = this.Data[i];
                this.Data = temp;
                this.IndexStart = 0;                                //Reset index start


            }
            else
            {
                this.Data = Arrays.copyOf(this.Data,
                        this.isEmpty() ? 1 : this.Size * 2);       //Just copy uncycled queue
            }
        }
        //Add element to array
        if( (this.IndexStart + this.Size) >= this.Data.length)
            this.Data[(this.IndexStart + this.Size) - this.Data.length] = element;  //Cycle queue
        else
            this.Data[this.IndexStart + this.Size] = element;

        this.Size++;

    }
    ///////////////////////////////
    //Delete element from the queue
    ///////////////////////////////
    public Type dequeue() throws Exception
    {
        if(this.isEmpty())          //Can we dequeue?
        {
            resetQueue();
            throw new Exception("Cannot dequeue. The queue is empty.");
        }
        this.Size--;
        Type returnedValue = this.Data[this.IndexStart];
        this.Data[this.IndexStart] = null;
        if( (this.IndexStart + 1) == this.Data.length)        //Get next element from cycled queue
            this.IndexStart = 0;
        else
            this.IndexStart++;
        return returnedValue;

    }
    /////////////////////
    ///Return queue's size
    //////////////////////
    public int size()
    {
        return this.Size;
    }
    /////////////////
    //Is queue empty?
    /////////////////
    public boolean isEmpty()
    {
        if(this.Size == 0)
            return true;
        else
            return false;
    }
    ////////////////////////////////////
    //Return the first element in queue
    ////////////////////////////////////
    public Type front() throws Exception
    {
        if(this.isEmpty())
        {
            resetQueue();
            throw new Exception("Cannot get element. Stack is empty");
        }
        else
            return this.Data[this.IndexStart];
    }

    ////////////////////////////
    //Get List of queue elements
    ////////////////////////////
    public List<Type> getQueueElements() throws Exception {
        List<Type> list = new ArrayList<>();
        int start = this.IndexStart;
        int size = this.Size;
        //Full list
        while (size != 0) {
            size--;
            list.add(this.Data[start]);
            //Get next element from cycled queue
            if ((start + 1) == this.Data.length)
                start = 0;
            else
                start++;
        }
        return list;
    }
}
