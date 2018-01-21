package com.molarmak.bookapp.modules.general.bookInfo.model;

import android.content.Context;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.MyApplication;
import com.molarmak.bookapp.modules.general.bookInfo.presenter.BookInfoPresenterCallback;
import com.molarmak.bookapp.storage.Hand.Database;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public class BookInfoService {

    private Database database = new Database();
    private Context context = MyApplication.getContext();

    public void addBook(Book book, BookInfoPresenterCallback callback) {
        new Thread(() -> {
            if(database.saveBookToDatabase(book)) {
                callback.endAddBook();
            } else {
                callback.onError(context.getString(R.string.errorAddBook));
            }
        }).start();
    }

    public void getBook(String token, BookInfoPresenterCallback callback) {
        new Thread(() -> callback.endLoadBookFromDB(database.getBookByToken(token))).start();
    }

    public void remakeBook(Book book, BookInfoPresenterCallback callback) {
        new Thread(() -> {
            if(database.remakeBookInDatabase(book)) {
                callback.endRemakeBook();
            } else {
                callback.onError(context.getString(R.string.errorRemakeBook));
            }
        }).start();
    }
}
