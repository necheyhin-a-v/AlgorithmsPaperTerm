package com.company.Task5;

/**
 * Created by ALEKSANDR NECHEUKHIN on 25.12.2016.
 */
public class Hash3 extends AbstractHash {
    Integer SimpleNumber1;
    Integer SimpleNumber2;

    Hash3(int size) throws Exception {
        super(size);
        SimpleNumber1 = super.randSimpleNumber();
        Thread.sleep(1);
        SimpleNumber2 = super.randSimpleNumber();

    }

    protected Integer doHash(Object object) {
        return object.hashCode();
    }
}
