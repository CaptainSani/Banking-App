package com.sani.World.Banking.App.utils;

import java.time.Year;

public class AccountUtils {

    public static String ACCOUNT_EXISTS_CODE = "001";
    public static String ACCOUNT_EXIXTS_MESSAGE = "This user already has an account created!";

    public static String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Your account has been created successfully!";

    public static String ACCOUNT_NUMBER_NOT_EXISTS_CODE = "003";
    public static String ACCOUNT_NUMBER_NOT_FOUND_MESSAGE = "Provided Account Number Does Not Exist";

    public static String ACCOUNT_CREDITED_SUCCESS_CODE = "004";
    public static String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "Account Credited Successfully";

    public static String INSUFFICIENT_BALANCE_CODE = "005";
    public static String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";

    public static String ACCOUNT_DEBITED_SUCCESS_CODE = "006";
    public static String ACCOUNT_DEBITED_SUCCESS_MESSAGE = "Transaction Successful";

    public static String ACCOUNT_NUMBER_FOUND_CODE = "007";
    public static String ACCOUNT_NUMBER_FOUND_MESSAGE = "Account number found";

    public static String TRANSFER_SUCCESSFUL_CODE = "008";
    public static String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer Successful";


    public static String generateAccountNumber(){
        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;


        int randomNUmber = (int) Math.floor(Math.random() * (max -  min + 1) + min);

        String year = String.valueOf(currentYear);
        String randomNum = String.valueOf(randomNUmber);

        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randomNum).toString();
    }
}
