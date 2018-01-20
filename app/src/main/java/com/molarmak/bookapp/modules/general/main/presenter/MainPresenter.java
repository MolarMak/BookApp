package com.molarmak.bookapp.modules.general.main.presenter;

import android.util.Log;

import com.molarmak.bookapp.modules.general.main.model.remote.BookService;
import com.molarmak.bookapp.modules.general.main.view.MainView;

/**
 * Created by Maxim on 1/20/18.
 */

public class MainPresenter implements MainPresenterCallback {

    private MainView view;
    private BookService service = new BookService();
    private final String TAG = "MainPresenter";

    @Override
    public void onLoadBookList(MainView view) {
        try {
            this.view = view;
            view.onBookListLoaded(service.loadBookFromDatabase());
        } catch (Exception e) {
            onError("Unhandled error when load book list");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {
        if(view != null) {
            view.onError(error);
        } else {
            Log.e(TAG, "view null " + error);
        }
    }
}
