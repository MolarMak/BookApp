package com.molarmak.bookapp.modules.general.main.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.modules.general.main.model.items.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxmamuta on 8/16/17.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Book> repoList = new ArrayList<>();
    private Context context;

    public void add(Book item) {
        try {
            repoList.add(item);
            notifyItemInserted(repoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void del(Book item) {
        try {
            for(int i = 0; i < repoList.size(); i++) {
             //   if(item.getId().equals(repoList.get(i).getId())) {
             //       repoList.remove(repoList.get(i));
             //       notifyItemRemoved(i);
           //     }
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

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        try {
            final Book Item = repoList.get(i);
            if (Item != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookImage;
        private TextView bookName, bookAuthor, bookGenre, bookPages;

        public ViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookGenre = itemView.findViewById(R.id.bookGenre);
            bookPages = itemView.findViewById(R.id.bookPages);
        }
    }
}
