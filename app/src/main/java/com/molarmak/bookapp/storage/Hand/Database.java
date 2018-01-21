package com.molarmak.bookapp.storage.Hand;

import com.molarmak.bookapp.helper.GenerateToken;
import com.molarmak.bookapp.storage.Items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

public class Database {

    public boolean saveBookToDatabase(Book book) {
        try {
            book.setToken(GenerateToken.generate());
            HelperFactory.getHelper().getBookDAO().create(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public Book getBookByToken(String token) {
        try {
            List<Book> bookList = loadBookList();
            for(int i = 0; i < bookList.size(); i++) {
                if(bookList.get(i).getToken().equals(token)) {
                    return bookList.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
