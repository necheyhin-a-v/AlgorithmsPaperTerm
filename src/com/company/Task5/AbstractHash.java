package com.company.Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class for collistions
 */
public abstract class AbstractHash {
    private int TableSize;
    private Object[] Data;
    private Integer[] ListCollisions;
    private int CollisionCount;

    AbstractHash(int size) {
        this.TableSize = size;
        this.Data = new Object[size];
        this.CollisionCount = 0;
        this.ListCollisions = new Integer[this.TableSize];
        for (int i = 0; i < ListCollisions.length; i++)
            ListCollisions[i] = 0;
    }

    public int getCollisionCount() {
        return CollisionCount;
    }

    public Integer[] getCollisions() {
        return ListCollisions;
    }

    ////////////////////////////////
    //Method generates simple number
    ////////////////////////////////
    public Integer randSimpleNumber() {
        Random random = new Random(System.currentTimeMillis());
        ArrayList<Integer> list = new ArrayList<Integer>();
        int max = random.nextInt(10000);
        for (int i = 2; i < max; i++) {
            int k = 0;
            for (int j = 2; j < i / 2; j++) {
                if (i % j == 0)
                    k++;
            }
            if (k == 0)
                list.add(i);

        }
        if (list.size() == 0)
            return randSimpleNumber();
        return list.get(list.size() - 1);
    }

    ///////////////////////////
    //Add new data to hash table
    ///////////////////////////
    final void add(Object object) {
        int index = doHash(object);//% TableSize;
        index = Math.abs(index) % TableSize;
        //List was not created
        if (Data[index] == null) {
            List list = new ArrayList();
            list.add(object);
            this.Data[index] = list;
        } else {
            //Searching list. If object exists then return index
            List list = (List) this.Data[index];
            //Object has already been added before
            if (list.contains(object))
                return;
            //Add object to the end of list
            list.add(object);
            CollisionCount++;
            ListCollisions[index]++;
        }
    }

    public int getCountCollisions() {
        return CollisionCount;
    }

    ////////////////////////////////////////
    //Returns object from hash table or null
    ////////////////////////////////////////
    final public Object search(Object object) {
        int index = Math.abs(doHash(object)) % TableSize;
        if (Data[index] != null) {
            //transform data to list
            List list = (List) Data[index];
            if (list.contains(object))
                return list.get(list.indexOf(object));
            else
                return null;
        }
        return null;
    }

    ///////////////////////////////
    //Delete object from hash table
    ///////////////////////////////
    final public void delete(Object object) {
        if (search(object) != null) {
            int index = Math.abs(doHash(object)) % TableSize;
            List list = (List) this.Data[index];
            if (list.size() == 1)
                this.Data[index] = null;                //Remove the whole list
            else
                list.remove(list.indexOf(object));      //Remove only element
        }
    }

    //Abstract method for different hash algorithms
    protected abstract Integer doHash(Object object);

}
