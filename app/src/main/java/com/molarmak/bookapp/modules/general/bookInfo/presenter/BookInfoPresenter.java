package com.molarmak.bookapp.modules.general.bookInfo.presenter;

import android.util.Log;

import com.molarmak.bookapp.modules.general.bookInfo.model.BookInfoService;
import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoView;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public class BookInfoPresenter implements BookInfoPresenterCallback {

    private BookInfoView view;
    private BookInfoService service = new BookInfoService();
    private final String TAG = "BookInfoPresenter";

    @Override
    public void startAddBook(BookInfoView view) {
        try {
            this.view = view;
            Book book = startValidateInput();
            if(book != null) {
                service.addBook(book, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endAddBook() {
        if(view != null) {
            view.onBookAdded();
        } else Log.e(TAG, "view null, but bool added");
    }

    @Override
    public void errorAddBook(String error) {
        if(view != null) {
            view.onError(error);
        } else Log.e(TAG, "view null " + error);
    }

    @Override
    public void startRemakeBook(BookInfoView view) {

    }

    private Book startValidateInput() {
        try {
            Book book = view.getBookInfo();

            if(book == null) {
                view.onError("Ошибка ввода данных");
                return null;
            }

            if(book.getImage().length == 0) {
                view.onError("Ошибка при заполнении фотографии");
                return null;
            }

            if(book.getName().length() < 1) {
                view.onError("Ошибка при заполнении имени книги");
                return null;
            }

            if(book.getAuthor().length() < 1) {
                view.onError("Ошибка при заполнении автора книги");
                return null;
            }

            if(book.getGender().length() < 1) {
                view.onError("Ошибка при заполнении жанра книги");
                return null;
            }

            if(book.getPages() == 0) {
                view.onError("Ошибка при заполнении страниц книги");
                return null;
            }

            return book;
        } catch (Exception e) {
            view.onError("Ошибка ввода данных");
            e.printStackTrace();
        }
        return null;
    }
}
