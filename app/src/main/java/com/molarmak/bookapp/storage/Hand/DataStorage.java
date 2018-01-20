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

public class DataStorage extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 2;

    private BookDAO bookDAO = null;

    public DataStorage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Book.class);
            setupEmptyDatabase(MyApplication.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Book.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        bookDAO = null;
        super.close();
    }

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

    private void setupEmptyDatabase(Context context) {
        try {
            Book bookDB = new Book();

            for (int i = 0; i < 3; i++) {
                bookDB.setToken("exampleToken"+i);
                bookDB.setImage(loadTestImage(context));
                bookDB.setName("Алиса в стране чудес [Test " + i + "]");
                bookDB.setAuthor("Льюис Кэрролл");
                bookDB.setGender("Фантастика");
                bookDB.setPages(450);

                HelperFactory.getHelper().getBookDAO().create(bookDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
