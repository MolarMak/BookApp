package com.molarmak.bookapp.modules.general.bookInfo.presenter;

import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoView;

/**
 * Created by Maxim on 1/21/18.
 */

public interface BookInfoPresenterCallback {
    void startAddBook(BookInfoView view);
    void endAddBook();
    void errorAddBook(String error);
    void startRemakeBook(BookInfoView view);
}
