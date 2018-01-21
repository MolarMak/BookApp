package com.molarmak.bookapp.modules.general.main.presenter;

import android.content.Context;
import android.util.Log;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.MyApplication;
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
    private Context context = MyApplication.getContext();
    private final String TAG = "MainPresenter";

    @Override
    public void onStartLoadBookList(MainView view) {
        try {
            this.view = view;
            service.loadBookFromDatabase(this);
        } catch (Exception e) {
            onError(context.getString(R.string.errorLoadBookList));
            e.printStackTrace();
        }
    }

    @Override
    public void onEndLoadBookList(List<Book> bookList) {
        try {
            view.onBookListLoaded(bookList);
        } catch (Exception e) {
            onError(context.getString(R.string.errorLoadBookList));
            e.printStackTrace();
        }
    }

    @Override
    public void onStartDeleteBook(String token) {
        try {
            service.deleteBookFromDatabase(this, token);
        } catch (Exception e) {
            onError(context.getString(R.string.errorDeleteBook));
            e.printStackTrace();
        }
    }

    @Override
    public void onEndDeleteBook(String token) {
        try {
            view.onBookDeleted(token);
        } catch (Exception e) {
            onError(context.getString(R.string.errorDeleteBook));
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
