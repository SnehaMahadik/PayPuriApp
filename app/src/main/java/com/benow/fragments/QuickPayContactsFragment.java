package com.benow.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.adapters.QuickPayContactsListAdapter;
import com.benow.models.QuickPayContact;
import com.benow.models.QuickPayContacts;
import com.benow.network.requestbuilder.QuickpPayContactsRequestBuilder;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuickPayContactsFragment extends Fragment implements Response.Listener, Response.ErrorListener{
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private Context mContext;
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private Gson gson;
    private String json;
    private QuickPayContacts mQuickPayContacts;
    private ArrayList<QuickPayContact> mContacts;


    public QuickPayContactsFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quick_pay_contacts, container, false);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressbar.setVisibility(View.VISIBLE);

        // Inflate the layout for this fragment
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity=getActivity();

        CheckPermissionToReadContacts();

        readPhoneContacts();

        try {
            makeRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void makeRequest() throws JSONException {
        QuickpPayContactsRequestBuilder mQuickpPayContactsRequestBuilder = new QuickpPayContactsRequestBuilder(mContext);
        mQuickpPayContactsRequestBuilder.getQuickPayContacts(this,readPhoneContacts());
    }

    private void CheckPermissionToReadContacts() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private JSONObject readPhoneContacts() {
        Map<String, String> hashMap = new HashMap<>();
        Cursor cursor = null;
        ArrayList<String> contacts = null;
        JSONObject jsonObject=null;
        mContacts=new ArrayList<QuickPayContact>();

        String jsonData=null;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            int contactIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
            int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int phoneNumberIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int photoIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);
            String strContacts = "";
            contacts = new ArrayList<>();
            cursor.moveToFirst();
            do {
                String idContact = cursor.getString(contactIdIdx);
                String name = cursor.getString(nameIdx);
                String phoneNumber = cursor.getString(phoneNumberIdx);
             //   Uri uri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, photoIdIdx);
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(photoIdIdx));

                Pattern pattern = Pattern.compile("[^0-9]");
                Matcher matcher = pattern.matcher(phoneNumber);
                String number = matcher.replaceAll("");

                contacts.add(number);
                strContacts = strContacts + phoneNumber + ",";
                QuickPayContact mQuickPayContact=new QuickPayContact();
                mQuickPayContact.setMobileNo(number);
                mQuickPayContact.setContactName(name);
                mQuickPayContact.setImageUrl(uri);
                mContacts.add(mQuickPayContact);
                //...
            } while (cursor.moveToNext());

            jsonData = new Gson().toJson(contacts);
            HashMap <String, ArrayList<String>> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("contacts",contacts);

           jsonObject= new JSONObject(stringStringHashMap);




        }
        catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            if (cursor != null) {
                cursor.close();
            }
        }
        return jsonObject;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    readPhoneContacts();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        mProgressbar.setVisibility(View.GONE);
        Log.i("Error",error.toString());

    }

    @Override
    public void onResponse(Object response)
    {
        mProgressbar.setVisibility(View.GONE);
        Log.i("Success ",response.toString());

        if(response  instanceof QuickPayContacts)
        {

            mQuickPayContacts = (QuickPayContacts) response;
        //    if(mQuickPayContacts.getMobileNumbersUsingApp().size()> 0) {
                QuickPayContactsListAdapter mQuickPayContactsListAdapter = new QuickPayContactsListAdapter(mContext, mQuickPayContacts.getMobileNumbersUsingApp(),mContacts);
              //  mQuickPayContactsListAdapter.setOnOrderListClickListener(this);
                mRecyclerView.setAdapter(mQuickPayContactsListAdapter);
                mProgressbar.setVisibility(View.GONE);


           // }
        }
    }
}
