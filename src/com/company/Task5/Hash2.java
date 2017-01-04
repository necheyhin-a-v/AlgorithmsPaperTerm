package com.company.Task5;

/**
 * Created by ALEKSANDR NECHEUKHIN on 25.12.2016.
 */
public class Hash2 extends AbstractHash {
    private Integer SimpleNumber;

    Hash2(int size) {
        super(size);
        SimpleNumber = super.randSimpleNumber();
    }

    protected Integer doHash(Object object) {
        String objectString = object.toString();
        //Generate number from object
        double numberFromObject = 0;
        for (int i = 0; i < objectString.length(); i++) {
            numberFromObject += objectString.charAt(i) * i;
        }
        //numberFromObject *= SimpleNumber;
        return (int) numberFromObject * SimpleNumber;
    }
}