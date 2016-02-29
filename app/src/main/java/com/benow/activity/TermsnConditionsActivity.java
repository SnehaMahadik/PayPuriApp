package com.benow.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.benow.R;
import com.benow.network.Constants;
import com.benow.network.requestbuilder.QuickpPayContactsRequestBuilder;
import com.benow.network.requestbuilder.TnCRequestBuilder;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class TermsnConditionsActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener{
    TextView mTextViewTitle;
    static TextView mTextViewContent;
    ImageView imageCross;
    Context mContext;
    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsn_conditions);
        mContext=getApplicationContext();
        Intent i = getIntent();
        Bundle extras=i.getExtras();
         browser = (WebView) findViewById(R.id.webview);

        if(extras !=null) {

            if(extras.getString("TITLE").equalsIgnoreCase("Terms and Conditions"))
            {


                String url=Constants.TnC_URL;
               browser.loadUrl(url);
               // postJson(url);
            }
            else {
                String url=Constants.PRIVACY_POLICY_URL;
               browser.loadUrl(url);
               // postJson(url);
            }



        }


       /* imageCross= (ImageView) findViewById(R.id.buttonCross);
        imageCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        mTextViewContent= (TextView) findViewById(R.id.textViewContent);
        mTextViewTitle= (TextView) findViewById(R.id.textViewTitle);
        if(extras !=null) {
            // This is necessary for the retrv_value
            mTextViewContent.setText(extras.getString("DATA"));
            mTextViewTitle.setText(extras.getString("TITLE"));


        }*/
    }
    private void postJson(String url) {


       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e("Volley Error ::", "Failed" + error.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("USERNAME","ganesh");
                params.put("PASSWORD","ganesh");

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Basic Z2FuZXNoOmdhbmVzaA==");
                return headers;
            }

        };


        RequestQueue requestQueue1 = Volley.newRequestQueue(mContext);
        requestQueue1.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(mContext,error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Object response) {
        Toast.makeText(mContext, response.toString(), Toast.LENGTH_LONG).show();
        browser.loadDataWithBaseURL("x-data://base", response.toString(),
                "text/html", "UTF-8",
                null);
    }
}
