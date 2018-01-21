package com.molarmak.bookapp.storage.DAO;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.molarmak.bookapp.storage.Items.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maxim on 1/20/18.
 */

/**
 * Class need for create ORM connection with database
 */
public class BookDAO extends BaseDaoImpl<Book,Integer> {
    public BookDAO(ConnectionSource connectionSource, Class<Book> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Book> getAllBooks() throws SQLException {
        return this.queryForAll();
    }

    public Book getBook(int i) throws SQLException {
        return this.queryForId(i);
    }
}
