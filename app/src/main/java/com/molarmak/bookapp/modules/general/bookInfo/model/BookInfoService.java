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

/**
 * Service that connect BookInfoActivity with Database
 */
public class BookInfoService {

    private Database database = new Database();
    private Context context = MyApplication.getContext();

    /**
     * Function for add new book in new Thread
     * @param book
     * @param callback
     * Execute EndAddBook when book added successful or OnError if not
     */
    public void addBook(Book book, BookInfoPresenterCallback callback) {
        new Thread(() -> {
            if(database.saveBookToDatabase(book)) {
                callback.endAddBook();
            } else {
                callback.onError(context.getString(R.string.errorAddBook));
            }
        }).start();
    }

    /**
     * Function for get current Book, when user want to remake it
     * @param token
     * @param callback
     * Search Book in Database by token
     * If search success return Book Object, else return null
     */
    public void getBook(String token, BookInfoPresenterCallback callback) {
        new Thread(() -> callback.endLoadBookFromDB(database.getBookByToken(token))).start();
    }

    /**
     * Function for rewrite book with new data
     * @param book
     * @param callback
     * Execute EndRemakeBook Callback if result is successful, or OnError if not
     */
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
