package com.molarmak.bookapp.modules.general.bookInfo.presenter;

import android.content.Context;
import android.util.Log;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.MyApplication;
import com.molarmak.bookapp.modules.general.bookInfo.model.BookInfoService;
import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoView;
import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

/**
 * Class Presenter for BookInfoActivity
 * Connect BookInfoService and BookInfoActivity
 */
public class BookInfoPresenter implements BookInfoPresenterCallback {

    private BookInfoView view;
    private BookInfoService service = new BookInfoService();
    private final String TAG = "BookInfoPresenter";
    private Context context = MyApplication.getContext();

    /**
     * Method call, when user input data for new book
     * @param view
     */
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

    /**
     * Method Callback, execute when new book added successful
     */
    @Override
    public void endAddBook() {
        if(view != null) {
            view.onBookAdded();
        } else Log.e(TAG, "view null, but bool added");
    }

    /**
     * Method call, when user click to remake information about available book
     * Call method for load book from database by token
     * @param token
     * @param view
     */
    @Override
    public void startLoadBookFromDB(String token, BookInfoView view) {
        try {
            this.view = view;
            service.getBook(token, this);
        } catch (Exception e) {
            onError(context.getString(R.string.errorLoadBookInfo));
            e.printStackTrace();
        }
    }

    /**
     * Method Callback, execute when book info get from database and available to remake
     * @param book
     */
    @Override
    public void endLoadBookFromDB(Book book) {
        if(view != null) {
            if(book != null) {
                view.onBookLoaded(book);
            } else onError(context.getString(R.string.errorLoadBookInfo));
        } else Log.e(TAG, "view null, but book loaded");
    }

    /**
     * Method call, when user remake some information about available book and want to save it
     * @param token
     * @param view
     * Validate user input, and if successful save changes into database
     */
    @Override
    public void startRemakeBook(String token, BookInfoView view) {
        try {
            this.view = view;
            Book book = startValidateInput();
            if(book != null) {
                book.setToken(token);
                service.remakeBook(book, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method Callback, says that new information for available book save successful
     */
    @Override
    public void endRemakeBook() {
        if(view != null) {
            view.onBookAdded();
        } else Log.e(TAG, "view null, but book remaded");
    }

    /**
     * Method for show error
     * @param error
     */
    @Override
    public void onError(String error) {
        if(view != null) {
            view.onError(error);
        } else Log.e(TAG, "view null " + error);
    }

    /**
     * Method for validate user input
     * @return
     * Validate by book object, Name, Author, Genre, Pages
     * Check all object is not null, String length > 0 and Pages > 0
     */
    private Book startValidateInput() {
        try {
            Book book = view.getBookInfo();

            if(book == null) {
                view.onError(context.getString(R.string.errorInput));
                return null;
            }

            if(book.getImage().length == 0) {
                view.onError(context.getString(R.string.errorPhotoInput));
                return null;
            }

            if(book.getName().length() < 1) {
                view.onError(context.getString(R.string.errorNameInput));
                return null;
            }

            if(book.getAuthor().length() < 1) {
                view.onError(context.getString(R.string.errorAuthorInput));
                return null;
            }

            if(book.getGenre().length() < 1) {
                view.onError(context.getString(R.string.errorGenreInput));
                return null;
            }

            if(book.getPages() == 0) {
                view.onError(context.getString(R.string.errorPagesInput));
                return null;
            }

            return book;
        } catch (Exception e) {
            view.onError(context.getString(R.string.errorInput));
            e.printStackTrace();
        }
        return null;
    }
}
