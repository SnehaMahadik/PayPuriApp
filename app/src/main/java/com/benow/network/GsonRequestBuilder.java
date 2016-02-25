package com.benow.network;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GsonRequestBuilder<T> extends Request<T> {
    private Gson gson = new Gson();
    private final Class<T> clazz;
    private Map<String, String> headers;
    private final Response.Listener<T> listener;
    private Map<String, String> params;

    public GsonRequestBuilder(String url,int methodType,  Class<T> clazz, Map<String, String> headers,Map<String, String> params,
                              Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(methodType, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = params;
        setHeaders(createBasicAuthHeader("ganesh", "ganesh"));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }



    @Override
    public String getBodyContentType()
    {
        return "application/json; charset=utf-8";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return createBasicAuthHeader("ganesh", "ganesh");
    }


    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
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
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}