package com.molarmak.bookapp.modules.general.main.model;


import com.molarmak.bookapp.modules.general.main.presenter.MainPresenterCallback;
import com.molarmak.bookapp.storage.Hand.Database;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/20/18.
 */

public class BookService {

    private Database database = new Database();

    public void loadBookFromDatabase(MainPresenterCallback callback) {
        new Thread(() -> callback.onEndLoadBookList(database.loadBookList())).start();
    }

    public void deleteBookFromDatabase(MainPresenterCallback callback, String token) {
        new Thread(() -> {
            if(database.deleteBookFromDatabase(token)) {
                callback.onEndDeleteBook(token);
            } else {
                callback.onError("Unhandled error when delete book");
            }
        }).start();
    }

}
