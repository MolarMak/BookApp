package com.molarmak.bookapp.modules.general.main.model.remote;

import com.molarmak.bookapp.modules.general.main.model.items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public class BookService {

    public List<Book> loadBookFromDatabase() {
        List<Book> fakeData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fakeData.add(new Book());
        }
        return fakeData;
    }

}
