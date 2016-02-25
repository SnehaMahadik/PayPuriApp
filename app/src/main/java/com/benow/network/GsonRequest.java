package com.benow.network;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *  Class to create custom GSON request
 * */
public class GsonRequest<T> extends JsonRequest<T> {

    private final Gson mGson;
    private final Class<T> mClassType;
    private Map<String, String> mHeaders;
    private final Response.Listener<T> mListener;

//    public GsonRequest(int method, String url, Class<T> classType, JSONObject jsonRequest,
//                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
//        this(method, url, classType, null, jsonRequest, listener, errorListener);
//    }

    public GsonRequest(int method, String url, Class<T> classType,
                       JSONObject jsonRequest, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
        mGson = new Gson();
        mClassType = classType;
//        mHeaders = headers;
        mListener = listener;
        setHeaders(createBasicAuthHeader(Constants.USERNAME, Constants.PASSWORD));
    }

    @Override
    public String getBodyContentType()
    {
        return "application/json; charset=utf-8";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return createBasicAuthHeader(Constants.USERNAME, Constants.PASSWORD);
    }

//
    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    Map<String, String> createBasicAuthHeader(String username, String password) {
        Map<String, String> headerMap = new HashMap<String, String>();

        String credentials = username + ":" + password;
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
        headerMap.put("Content-Type", "application/json; charset=utf-8");
        return headerMap;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data, HttpHeaderParser.parseCharset
                    (networkResponse.headers));
            return Response.success(mGson.fromJson(json, mClassType),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}