package com.esyto.mylibrary;

/**
 * Created by lhxez on 2016/6/17.
 */
public class LibTools {
    private int mytest;
    public int test;
    public void setTest(int i){
        mytest = i*10;
        test = i+3;
    }
    public int getValue(){
        return mytest*2+test;
    }
}
