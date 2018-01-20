package com.molarmak.bookapp.modules.general.main.presenter;

import com.molarmak.bookapp.modules.general.main.view.MainView;

/**
 * Created by Maxim on 1/20/18.
 */

public interface MainPresenterCallback {
    void onLoadBookList(MainView view);
    void onError(String error);
}
