package com.company.Task5;

public class Hash1 extends AbstractHash {
    private Integer SimpleNumber1;
    private Integer Size;

    /////////////
    //Constructor
    /////////////
    Hash1(int size) throws Exception {
        super(size);
        this.Size = size;
        SimpleNumber1 = super.randSimpleNumber();
    }

    /////////
    //Hashing
    /////////
    protected Integer doHash(Object object) {
        String objectString = object.toString();
        //Generate number from object
        double numberFromObject = 0;
        for (int i = 0; i < objectString.length(); i++) {
            numberFromObject %= objectString.charAt(i);
            numberFromObject += objectString.charAt(i); //* SimpleNumber1;
        }
        return (int) numberFromObject % SimpleNumber1;
    }
}
