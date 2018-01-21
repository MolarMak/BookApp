package com.molarmak.bookapp.modules.general.bookInfo.view;

import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public interface BookInfoView {
    Book getBookInfo();
    void onError(String error);
    void onBookAdded();
}
