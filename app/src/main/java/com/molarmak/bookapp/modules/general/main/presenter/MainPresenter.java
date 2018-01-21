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

/**
 * Class Presenter for MainActivity
 * Connect BookService and MainActivity
 */
public class MainPresenter implements MainPresenterCallback {

    private MainView view;
    private BookService service = new BookService();
    private Context context = MyApplication.getContext();
    private final String TAG = "MainPresenter";

    /**
     * Method call when MainActivity started. Call methods for load available book list
     * @param view
     */
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

    /**
     * Method call when book list loaded
     * @param bookList
     */
    @Override
    public void onEndLoadBookList(List<Book> bookList) {
        try {
            view.onBookListLoaded(bookList);
        } catch (Exception e) {
            onError(context.getString(R.string.errorLoadBookList));
            e.printStackTrace();
        }
    }

    /**
     * Call when user want to delete book
     * @param token
     * delete book bu token in database
     */
    @Override
    public void onStartDeleteBook(String token) {
        try {
            service.deleteBookFromDatabase(this, token);
        } catch (Exception e) {
            onError(context.getString(R.string.errorDeleteBook));
            e.printStackTrace();
        }
    }

    /**
     * Methods call when book deleted successful
     * @param token
     */
    @Override
    public void onEndDeleteBook(String token) {
        try {
            view.onBookDeleted(token);
        } catch (Exception e) {
            onError(context.getString(R.string.errorDeleteBook));
            e.printStackTrace();
        }
    }

    /**
     * Show error
     * @param error
     */
    @Override
    public void onError(String error) {
        if(view != null) {
            view.onError(error);
        } else {
            Log.e(TAG, "view null " + error);
        }
    }
}
