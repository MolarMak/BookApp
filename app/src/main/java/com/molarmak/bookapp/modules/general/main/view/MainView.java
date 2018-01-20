package com.molarmak.bookapp.modules.general.main.view;

import com.molarmak.bookapp.storage.Items.Book;

import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public interface MainView {
    void onError(String error);
    void onBookListLoaded(List<Book> bookList);
    void showEmptyList();
}
