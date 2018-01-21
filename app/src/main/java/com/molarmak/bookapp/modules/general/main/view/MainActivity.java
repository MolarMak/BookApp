package com.molarmak.bookapp.modules.general.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoActivity;
import com.molarmak.bookapp.modules.general.main.presenter.MainPresenter;
import com.molarmak.bookapp.modules.general.main.presenter.MainPresenterCallback;
import com.molarmak.bookapp.modules.general.main.view.adapters.RecycleViewAdapter;
import com.molarmak.bookapp.storage.Items.Book;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements MainView {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recycler;
    private RecycleViewAdapter adapter;
    private CoordinatorLayout mainLayout;
    private TextView textEmptyList;
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
        textEmptyList = findViewById(R.id.textEmptyList);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new RecycleViewAdapter(this, presenter);
        fab.setOnClickListener(view -> {
            try {
                finish();
                Intent i = new Intent(getApplicationContext(), BookInfoActivity.class);
                i.putExtra(BookInfoActivity.BOOK_TYPE, BookInfoActivity.BOOK_ADD);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupBookList();
    }

    private void setupBookList() {
        try {
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
            recycler.setItemAnimator(new SlideInUpAnimator());
            swipeContainer.setOnRefreshListener(() -> presenter.onStartLoadBookList(MainActivity.this));
            presenter.onStartLoadBookList(this);
        } catch (Exception e) {
            onError(getString(R.string.setupBookListError));
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String error) {
        try {
            runOnUiThread(() -> {
                try {
                    Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            onError(getString(R.string.runMainThreadError));
            e.printStackTrace();
        }
    }

    @Override
    public void onBookListLoaded(List<Book> bookList) {
        try {
            runOnUiThread(() -> {
                try {
                    adapter.clear();
                    adapter.addBookList(bookList);
                    swipeContainer.setRefreshing(false);
                } catch (Exception e) {
                    onError(getString(R.string.addBookInListError));
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            onError(getString(R.string.runMainThreadError));
            e.printStackTrace();
        }
    }

    @Override
    public void onBookDeleted(String token) {
        try {
            runOnUiThread(() -> {
                try {
                    adapter.removeBook(token);
                } catch (Exception e) {
                    onError(getString(R.string.addBookInListError));
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            onError(getString(R.string.runMainThreadError));
            e.printStackTrace();
        }
    }

    @Override
    public void showEmptyList() {
        try {
            if(adapter.getItemCount() == 0) {
                textEmptyList.setVisibility(View.VISIBLE);
            } else {
                textEmptyList.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
