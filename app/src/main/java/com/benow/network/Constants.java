package com.benow.network;

/**
 * Created by swapnil13494 on 2/1/2016.
 */
public class Constants {

    public static String BASE_URL = "http://192.168.1.110:8080/";
    public static String ACCOUNT_BALANCE = BASE_URL+"upiproxy/getAccountBalance/12340987";
    public static String All_TRANSACTIONHISTORY_URL = "http://192.168.1.33:8080/paymentHistory/search/findByUsername?Username=ganesh";

    //http://192.168.1.105:8080/page/readTermsAndConditions
    public static String USERNAME = "ganesh";
    public static String PASSWORD = "ganesh";

    public static String USERNAME_ATH = "user";
    public static String QUICKPAY_CONTACTS_URL = BASE_URL+"quickPay/getContactDetails/ganesh";
    public static String CREATE_USER_URL = BASE_URL+"peerContact";
    public static String PASSWORD_ATH = "password";
    public static String TnC_URL = "https://www.google.co.in/intl/en/policies/terms/regional.html";

    public static String PRIVACY_POLICY_URL = "https://www.google.co.in/intl/en/policies/privacy/?fg=1";

    public static String ALL_ORDERS_URL =  "ecomm/getAllOrdersForUser/ganesh";
    public static String PAY_TO_PEER_CONTACT_URL =  "quickPay/processPayments";
}
