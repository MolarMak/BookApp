package com.molarmak.bookapp.modules.general.bookInfo.model;

import com.molarmak.bookapp.modules.general.bookInfo.presenter.BookInfoPresenterCallback;
import com.molarmak.bookapp.storage.Hand.Database;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public class BookInfoService {

    private Database database = new Database();

    public void addBook(Book book, BookInfoPresenterCallback callback) {
        new Thread(() -> {
            if(database.saveBookToDatabase(book)) {
                callback.endAddBook();
            } else {
                callback.errorAddBook("Unknown error when add book");
            }
        }).start();
    }
}
