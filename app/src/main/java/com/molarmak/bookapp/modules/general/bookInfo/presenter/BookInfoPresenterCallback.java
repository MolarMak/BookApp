package com.molarmak.bookapp.modules.general.bookInfo.presenter;

import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoView;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public interface BookInfoPresenterCallback {
    void startAddBook(BookInfoView view);
    void endAddBook();
    void startLoadBookFromDB(String token, BookInfoView view);
    void endLoadBookFromDB(Book book);
    void startRemakeBook(BookInfoView view);
    void onError(String error);
}
