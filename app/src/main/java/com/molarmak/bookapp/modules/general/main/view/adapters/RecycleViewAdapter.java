package com.molarmak.bookapp.modules.general.main.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.OnSwipeTouchListener;
import com.molarmak.bookapp.helper.SetImage;
import com.molarmak.bookapp.modules.general.bookInfo.view.BookInfoActivity;
import com.molarmak.bookapp.modules.general.main.presenter.MainPresenterCallback;
import com.molarmak.bookapp.modules.general.main.view.MainView;
import com.molarmak.bookapp.storage.Items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxmamuta on 8/16/17.
 */

/**
 * Class Adapter to show book list items
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Book> repoList = new ArrayList<>();
    private int pxToMove;
    private Context context;
    private MainView mainView;
    private MainPresenterCallback presenter;
    private final String TAG = "RecyclerMain";

    public RecycleViewAdapter(MainView mainView, MainPresenterCallback presenter) {
        this.mainView = mainView;
        this.presenter = presenter;
    }

    /**
     * Method for add book list
     * @param bookList
     * Items added one by one, because it needed for animation
     */
    public void addBookList(List<Book> bookList) {
        try {
            for(int i = 0; i < bookList.size(); i++) {
                repoList.add(bookList.get(i));
                notifyItemInserted(repoList.size());
            }
            mainView.showEmptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for remove book from list by token
     * @param token
     */
    public void removeBook(String token) {
        try {
            for(int i = 0; i < repoList.size(); i++) {
                if(repoList.get(i).getToken().equals(token)) {
                    repoList.remove(repoList.get(i));
                    notifyItemRemoved(i);
                    if(repoList.size() == 0)
                        mainView.showEmptyList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for clear list
     * Items remove one by one, because it needed for animation
     */
    public void clear() {
        try {
            for(int i = repoList.size()-1; i>=0; i--) {
                repoList.remove(i);
                notifyItemRemoved(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * Methods for display data, check it before add
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        try {
            final Book Item = repoList.get(i);
            if (Item != null) {
                if(Item.getToken() != null) {
                    setBasketView(viewHolder, Item.getToken());
                } else Log.e(TAG, "token of book null");

                if(Item.getImage() != null) {
                    if(Item.getImage().length > 0) {
                        SetImage.setImage(viewHolder.bookImage, Item.getImage());
                    } else SetImage.setImage(viewHolder.bookImage, new byte[5]);
                } else SetImage.setImage(viewHolder.bookImage, new byte[5]);

                if(Item.getName() != null) {
                    viewHolder.bookName.setText(Item.getName());
                } else viewHolder.bookName.setText("");

                if(Item.getAuthor() != null) {
                    viewHolder.bookAuthor.setText(Item.getAuthor());
                } else viewHolder.bookAuthor.setText("");

                if(Item.getGenre() != null) {
                    viewHolder.bookGenre.setText(Item.getGenre());
                } else viewHolder.bookGenre.setText("");

                if(Item.getPages() > 0) {
                    viewHolder.bookPages.setText(Item.getPages() + " " + context.getString(R.string.pages));
                } else viewHolder.bookPages.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    /**
     * Method create posibility to swipe-to-delete and click on item
     * @param viewHolder
     * @param token
     */
    private void setBasketView(ViewHolder viewHolder, String token) {
        try {
            Resources r = context.getResources();
            pxToMove = (int) -(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics()));
            final OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener(context) {
                @Override
                public void onSwipeLeft() {
                    if(viewHolder.infoLayout.getTranslationX() == 0) {
                        viewHolder.binLayout.setVisibility(View.VISIBLE);
                        viewHolder.binLayout.setAlpha(0.0f);
                        viewHolder.binLayout.animate().alpha(1.0f).setDuration(300).start();
                        viewHolder.infoLayout.animate().translationX(pxToMove).setDuration(250).start();
                    }
                }
                @Override
                public void onSwipeRight() {
                    if(viewHolder.infoLayout.getTranslationX() != 0) {
                        viewHolder.binLayout.setVisibility(View.GONE);
                        viewHolder.infoLayout.animate().translationX(0).setDuration(250).start();
                    }
                }
                @Override
                public void onSingleTapUp() {
                    if(viewHolder.infoLayout.getTranslationX() != 0) {
                        onSwipeRight();
                    } else {
                        navigateToRemakeScreen(token);
                    }
                }
            };
            viewHolder.itemView.setOnTouchListener(swipeTouchListener);
            viewHolder.binLayout.setOnClickListener(view -> {
                try {
                    swipeTouchListener.onSwipeRight();
                    presenter.onStartDeleteBook(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Item open BookInfoActivity with filled data about current book
     * when user click on item
     * @param token
     */
    private void navigateToRemakeScreen(String token) {
        try {
            ((AppCompatActivity) context).finish();
            Intent intent = new Intent(context, BookInfoActivity.class);
            intent.putExtra(BookInfoActivity.BOOK_TYPE, BookInfoActivity.BOOK_CHANGE);
            intent.putExtra(BookInfoActivity.BOOK_TOKEN, token);
            context.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookImage;
        private TextView bookName, bookAuthor, bookGenre, bookPages;
        private LinearLayout infoLayout;
        private RelativeLayout binLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookGenre = itemView.findViewById(R.id.bookGenre);
            bookPages = itemView.findViewById(R.id.bookPages);
            infoLayout = itemView.findViewById(R.id.infoLayout);
            binLayout = itemView.findViewById(R.id.binLayout);
        }
    }
}
