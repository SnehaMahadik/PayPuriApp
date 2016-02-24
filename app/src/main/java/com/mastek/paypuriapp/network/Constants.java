package com.mastek.paypuriapp.network;

import android.util.Log;

/**
 * Created by swapnil13494 on 2/1/2016.
 */
public class Constants {

//    public static String BASE_URL = "http://192.168.3.160:8080/ecomm/";
    public static String BASE_URL = "http://192.168.2.176:8080/ecomm/";
    public static String ACCOUNT_BALANCE = "http://192.168.2.176:8080/upiproxy/getAccountBalance/12340987";
    public static String All_TRANSACTIONHISTORY_URL = "http://192.168.1.35:8080/paymentHistory/search/findByUsername?Username=ganesh";
  //  public static String BASE_URL = "http://192.168.1.34:8080/ecomm/";




    // http://localhost:8080/ecomm/getAllOrders

    public static String USERNAME = "ganesh";
    public static String PASSWORD = "ganesh";

    public static String USERNAME_ATH = "user";
    public static String PASSWORD_ATH = "password";

    public static String NO_MORE_RESULTS = "NO_MORE_RESULTS";
    public static String SERVER_ERROR = "SERVER_ERROR";
    public static String APP_KEY = "APP_KEY";
    public static int REQUEST_TIMEOUT = 4000;
    public static int MAX_RETRIES = 2;
    public static Float BACKOFF_MULT =1.0f;
    public static int TAG_FOR_YOU = 1;
    public static String NETWORK_ERROR = "NETWORK_ERROR";
    public static String NO_INTERNET_CONNECTION = "NO_INTERNET_CONNECTION";
    public static String CONNECTION_TIME_OUT = "CONNECTION_TIME_OUT";
     public static int TAG_OFFERS = 10;

    public static void error(String msg)
    {
        Log.e("TAG", "" + msg);
    }

    public static void debug(String msg) {
        Log.d("TAG", "" + msg);
    }
}
