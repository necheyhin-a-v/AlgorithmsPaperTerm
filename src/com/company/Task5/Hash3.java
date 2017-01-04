package com.company.Task5;

/**
 * Created by ALEKSANDR NECHEUKHIN on 25.12.2016.
 */
public class Hash3 extends AbstractHash {
    Hash3(int size) throws Exception {
        super(size);
    }

    protected Integer doHash(Object object) {
        return object.hashCode();
    }
}
