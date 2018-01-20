package com.molarmak.bookapp.modules.general.main.presenter;

import com.molarmak.bookapp.modules.general.main.view.MainView;
import com.molarmak.bookapp.storage.Items.Book;

import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public interface MainPresenterCallback {
    void onStartLoadBookList(MainView view);
    void onEndLoadBookList(List<Book> bookList);
    void onStartDeleteBook(String token);
    void onEndDeleteBook(String token);
    void onError(String error);
}
