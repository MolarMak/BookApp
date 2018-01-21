package com.molarmak.bookapp.storage.Hand;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.MyApplication;
import com.molarmak.bookapp.storage.DAO.BookDAO;
import com.molarmak.bookapp.storage.Items.Book;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;

/**
 * Created by Maxim on 7/18/2017.
 */

/**
 * Class shell for ORM with SQLite
 */
public class DataStorage extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 3;

    private BookDAO bookDAO = null;

    public DataStorage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method first create database tables and insert test data
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Book.class);
            setupEmptyDatabase(MyApplication.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method calls, when database version ++
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Book.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method calls when database connection is closed
     */
    @Override
    public void close() {
        bookDAO = null;
        super.close();
    }

    /**
     * @return Object for interaction with data
     */
    public BookDAO getBookDAO() {
        if(bookDAO == null) {
            try {
                bookDAO = new BookDAO(getConnectionSource(), Book.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookDAO;
    }

    /**
     * Method setup test data when database is created
     * @param context
     */
    private void setupEmptyDatabase(Context context) {
        try {
            Book bookDB = new Book();

            for (int i = 0; i < 3; i++) {
                bookDB.setToken("exampleToken"+i);
                bookDB.setImage(loadTestImage(context));
                bookDB.setName("Алиса в стране чудес [Test " + i + "]");
                bookDB.setAuthor("Льюис Кэрролл");
                bookDB.setGenre("Фантастика");
                bookDB.setPages(450);

                HelperFactory.getHelper().getBookDAO().create(bookDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method load test image for book from drawable
     * @param context
     * @return
     */
    private byte[] loadTestImage(Context context) {
        try {
            Drawable d = context.getDrawable(R.drawable.alice_test_book);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[5];
    }
}
