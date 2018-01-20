package com.molarmak.bookapp.storage.Hand;

import com.molarmak.bookapp.storage.Items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public class Database {

    private void saveBookToDatabase(Book book) {
        //todo generate token
    }

    public boolean deleteBookFromDatabase(String token) {
        try {
            List<Book> bookList = HelperFactory.getHelper().getBookDAO().getAllBooks();
            for(Book book : bookList) {
                if(book.getToken().equals(token)) {
                    HelperFactory.getHelper().getBookDAO().delete(book);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
