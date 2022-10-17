package com.mohamed.capstonebankdemo.helpers;

import java.util.Random;

//This is a helper class to generate an account number for users
public class GenAccountNumber {
    public static int generateAccountNumber(){
        int accountNumber;
        Random randomNum = new Random();
        int limit = 1000;
        accountNumber = limit * randomNum.nextInt(limit);
        return accountNumber;
    }
}
