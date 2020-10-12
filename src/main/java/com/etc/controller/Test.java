package com.etc.controller;

public class Test {
    public static void main(String args[]){
        int a=A.B(10);
        System.out.println(a);

    }

}
class A{
    static int B(int i){
        try{
            Thread.sleep(1000);
            return i;
        }
        catch (Exception e){

        }
        finally {
            i=0;
        }
        return  66;
    }
}
