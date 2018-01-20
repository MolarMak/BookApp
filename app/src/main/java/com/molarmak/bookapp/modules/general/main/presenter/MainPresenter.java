package com.molarmak.bookapp.modules.general.main.presenter;

import android.util.Log;

import com.molarmak.bookapp.modules.general.main.model.BookService;
import com.molarmak.bookapp.modules.general.main.view.MainView;
import com.molarmak.bookapp.storage.Items.Book;

import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public class MainPresenter implements MainPresenterCallback {

    private MainView view;
    private BookService service = new BookService();
    private final String TAG = "MainPresenter";

    @Override
    public void onStartLoadBookList(MainView view) {
        try {
            this.view = view;
            service.loadBookFromDatabase(this);
        } catch (Exception e) {
            onError("Unhandled error when start load book list");
            e.printStackTrace();
        }
    }

    @Override
    public void onEndLoadBookList(List<Book> bookList) {
        try {
            view.onBookListLoaded(bookList);
        } catch (Exception e) {
            onError("Unhandled error when end load book list");
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
