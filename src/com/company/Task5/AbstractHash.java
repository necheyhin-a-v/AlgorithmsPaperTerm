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
    private int Collisions;

    AbstractHash(int size) {
        this.TableSize = size;
        this.Data = new Object[size];
        this.Collisions = 0;
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
        index = index % TableSize;
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
            Collisions++;
        }
    }

    public int getCountCollisions() {
        return Collisions;
    }

    ////////////////////////////////////////
    //Returns object from hash table or null
    ////////////////////////////////////////
    final public Object search(Object object) {
        int index = doHash(object) % TableSize;
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
            int index = doHash(object) % TableSize;
            List list = (List) this.Data[index];
            if (list.size() == 1)
                this.Data[index] = null;       //Remove the whole list
            else
                list.remove(list.indexOf(object));      //Remove only element
        }
    }

    //Abstract method for different hash algorithms
    protected abstract Integer doHash(Object object);

}
