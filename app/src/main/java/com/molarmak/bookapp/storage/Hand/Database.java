package com.molarmak.bookapp.storage.Hand;

import com.molarmak.bookapp.storage.Items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public class Database {

    private void saveBookToDatabase(Book book) {

    }

    private void deleteBookFromDatabase(Book book) {

    }

    public List<Book> loadBookList() {
        try {
            return HelperFactory.getHelper().getBookDAO().getAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
