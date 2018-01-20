package com.molarmak.bookapp.modules.general.main.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.modules.general.main.model.items.Book;
import com.molarmak.bookapp.modules.general.main.presenter.MainPresenter;
import com.molarmak.bookapp.modules.general.main.presenter.MainPresenterCallback;
import com.molarmak.bookapp.modules.general.main.view.adapters.RecycleViewAdapter;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements MainView {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recycler;
    private RecycleViewAdapter adapter = new RecycleViewAdapter();
    private CoordinatorLayout mainLayout;
    private FloatingActionButton fab;

    private MainPresenterCallback presenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(R.color.colorPrimaryDark);
        mainLayout = findViewById(R.id.mainLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupBookList();
    }

    private void setupBookList() {
        try {
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
            recycler.setItemAnimator(new SlideInUpAnimator());
            swipeContainer.setOnRefreshListener(() -> presenter.onLoadBookList(MainActivity.this));
            presenter.onLoadBookList(this);
        } catch (Exception e) {
            onError("Setup Book List error");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {
        try {
            Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBookListLoaded(List<Book> bookList) {
        try {
            adapter.clear();
            adapter.addBookList(bookList);
            swipeContainer.setRefreshing(false);
        } catch (Exception e) {
            onError("Add Book in List error");
            e.printStackTrace();
        }
    }

}
