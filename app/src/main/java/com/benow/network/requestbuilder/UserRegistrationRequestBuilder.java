package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.benow.fragments.MobileRegisterValidationFragment;
import com.benow.fragments.OTPValidationFragment;
import com.benow.fragments.SelectBankFragment;
import com.benow.fragments.UserDetailsFragment;
import com.benow.models.APIResponse;
import com.benow.models.AppUser;
import com.benow.models.SendOTPResponse;
import com.benow.models.ValidateOTPResponse;
import com.benow.network.Constants;
import com.benow.network.GsonRequest;
import com.benow.network.GsonRequestBuilder;


import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by swapnil13494 on 2/17/2016.
 */
public class UserRegistrationRequestBuilder {

    private GsonRequestBuilder gsonRequestBuilder;

    //    private String METHOD_GET_ALL_ORDERS = "getAllOrders";
    private String METHOD_MOBILE_SEND_OTP = "registration/sendOTP/";
    private String METHOD_MOBILE_Validate_OTP = "registration/checkOTP/";
    private String METHOD_MOBILE_Validate_username = "registration/checkUserIdAvailability/";
    private String METHOD_MOBILE_GET_ACCOUNTS = "upiproxy/getaccounts";
    private String METHOD_MOBILE_REGISTER_USER = "/appUsers";
    private RequestQueue mQueue;
    private HashMap<String, String> params;

    public UserRegistrationRequestBuilder(Context context) {
        mQueue =  Volley.newRequestQueue(context);//CustomVolleyRequestQueue.getInstance(context)
                //.getRequestQueue();
    }

    public void sendOTP(MobileRegisterValidationFragment mobileRegisterValidationFragment, String mobileNo) {

        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL + METHOD_MOBILE_SEND_OTP + mobileNo, Request.Method.POST, APIResponse.class, null, null, mobileRegisterValidationFragment, mobileRegisterValidationFragment);
        mQueue.add(gsonRequestBuilder);
    }

    public void resendOTP(OTPValidationFragment otpValidationFragment, String mobileNo) {

        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL + METHOD_MOBILE_SEND_OTP + mobileNo, Request.Method.POST, SendOTPResponse.class, null, null, otpValidationFragment, otpValidationFragment);
        mQueue.add(gsonRequestBuilder);
    }

    public void validateOTP(OTPValidationFragment otpValidationFragment, String mobileNo, String OTP) {

        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL + METHOD_MOBILE_Validate_OTP + mobileNo + "/" + OTP, Request.Method.POST, ValidateOTPResponse.class, null, null, otpValidationFragment, otpValidationFragment);
        mQueue.add(gsonRequestBuilder);
    }

    public void checkUserIdAvailability(UserDetailsFragment userDetailsFragment, String userName) {

        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL + METHOD_MOBILE_Validate_username + userName, Request.Method.POST, APIResponse.class, null, null, userDetailsFragment, userDetailsFragment);
        mQueue.add(gsonRequestBuilder);
    }

    public void getaccounts(SelectBankFragment selectBankFragment, HashMap<String, String> params){
        params = new HashMap<>();
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL + METHOD_MOBILE_GET_ACCOUNTS, Request.Method.POST, APIResponse.class, null, params, selectBankFragment, selectBankFragment);
        mQueue.add(gsonRequestBuilder);
    }

    public void userRegistration(UserDetailsFragment userDetailsFragment, String username, String email,String password, String referralCode, String mobileNo){
        // Post params to be sent to the server
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("username",username);
//        params.put("password","nnnnnnnn");
//        params.put("email",email);
//        params.put("mobileNumber",mobileNo);
//        params.put("parentUsername","parent");
//        params.put("referralCode", referralCode);
//
//        CustomJSONObjectRequest customJSONObjectRequest = new CustomJSONObjectRequest(Request.Method.POST, Constants.URL + METHOD_MOBILE_REGISTER_USER,new JSONObject(params), userDetailsFragment, userDetailsFragment);
//        customJSONObjectRequest.setShouldCache(false);
//        mQueue.add(customJSONObjectRequest);

    //        JsonObjectRequest req = new JsonObjectRequest(Constants.URL + METHOD_MOBILE_REGISTER_USER, new JSONObject(params),
    //                new Response.Listener<JSONObject>() {
    //                    @Override
    //                    public void onResponse(JSONObject response) {
    //                        try {
    //                            VolleyLog.v("Response:%n %s", response.toString(4));
    //                        } catch (JSONException e) {
    //                            e.printStackTrace();
    //                        }
    //                    }
    //                }, new Response.ErrorListener() {
    //                       @Override
    //            public void onErrorResponse(VolleyError error) {
    //                VolleyLog.e("Error: ", error.getMessage());
    //            }
    //
    //        }
    //        ){
    //            @Override
    //            public Map<String, String> getHeaders() throws AuthFailureError {
    //                Map<String, String> headerMap = new HashMap<String, String>();
    //
    //                String credentials = "ganesh" + ":" + "ganesh";
    //                String base64EncodedCredentials =
    //                        Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    //                headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
    //                headerMap.put("Content-Type", "application/json; charset=utf-8");
    //                return headerMap;
    //            }
    //        };
    //        req.setShouldCache(false);
    //        mQueue.add(req);
//        mQueue.add(req);


        params = new HashMap<>();
        params.put("username",username);
        params.put("password","1234");
        params.put("email",email);
        params.put("mobileNumber",mobileNo);
        params.put("parentUsername","parent");
        params.put("referralCode",referralCode);

//        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.URL + METHOD_MOBILE_REGISTER_USER, Request.Method.POST, AppUser.class, null, params, userDetailsFragment, userDetailsFragment);
//        mQueue.add(gsonRequestBuilder);


        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.BASE_URL + METHOD_MOBILE_REGISTER_USER, AppUser.class, new JSONObject(params),userDetailsFragment, userDetailsFragment);
        mQueue.add(gsonRequest);
    }

}
