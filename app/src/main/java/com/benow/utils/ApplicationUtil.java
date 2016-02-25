package com.benow.utils;

import android.app.Application;
import android.content.Context;
//import android.support.multidex.MultiDex;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
//import com.crashlytics.android.Crashlytics;
//import com.parse.Parse;
//import com.parse.ParseInstallation;

//import io.fabric.sdk.android.Fabric;
//import outletwise.com.twentydresses.model.database.greenbot.DaoSession;
//import outletwise.com.twentydresses.utilities.database.DBHelper;
//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User-PC on 11-08-2015.
 */
public final class ApplicationUtil extends Application {

    private RequestQueue mRequestQueue;
    private static ApplicationUtil mInstance;
    //private DBHelper mDbHelper;

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
       // mDbHelper = new DBHelper(mInstance);

        //Crash Reporting
       // Fabric.with(this, new Crashlytics());

        //Parse config
      //  Parse.initialize(this, getString(R.string.parse_appId), getString(R.string.parse_clientKey));
      //  ParseInstallation.getCurrentInstallation().saveInBackground();

        //Set Default font for whole application
       /* CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/PrimaryFont_Proxima.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );*/
    }

    public static ApplicationUtil getInstance() {
        return mInstance;
    }

    /*public static DaoSession getNewSession() {
        return getInstance().mDbHelper.getSession(true);
    }*/

   /* public static DaoSession getSession() {
        return getInstance().mDbHelper.getSession(false);
    }*/


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //  MultiDex.install(this);
    }
}
