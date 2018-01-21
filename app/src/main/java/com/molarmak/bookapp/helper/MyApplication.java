package com.molarmak.bookapp.helper;

import android.app.Application;
import android.content.Context;

import com.molarmak.bookapp.storage.Hand.HelperFactory;

/**
 * Created by Maxim on 1/20/18.
 */

/**
 * General class of application
 * Initialize database connection and have possibility to return Context
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            HelperFactory.setHelper(getApplicationContext());
            mContext = this;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        try {
            HelperFactory.releaseHelper();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onTerminate();
    }

    public static Context getContext(){
        return mContext;
    }
}