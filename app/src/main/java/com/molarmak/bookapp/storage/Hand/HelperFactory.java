package com.molarmak.bookapp.storage.Hand;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by Maxim on 7/18/2017.
 */

/**
 * Method open and close database connection
 */
public class HelperFactory {
    private static DataStorage databaseHelper;

    public static DataStorage getHelper() {
        return databaseHelper;
    }

    public static void setHelper(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DataStorage.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
