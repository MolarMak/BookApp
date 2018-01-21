package com.molarmak.bookapp.modules.general.bookInfo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.molarmak.bookapp.R;

public class BookInfoActivity extends AppCompatActivity {

    private ImageView bookImage;
    private EditText bookName, bookAuthor, bookGenre, bookPages;
    private Button actionButton;

    public static final String BOOK_TYPE = "book_type";
    public static final String BOOK_ADD = "add_book";
    public static final String BOOK_CHANGE = "change_book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_book_info);
            bookImage = findViewById(R.id.bookImage);
            bookName = findViewById(R.id.bookName);
            bookAuthor = findViewById(R.id.bookAuthor);
            bookGenre = findViewById(R.id.bookGenre);
            bookPages = findViewById(R.id.bookPages);
            actionButton = findViewById(R.id.actionButton);
            setupButtonClick();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupButtonClick() {
        try {
            String bookChangeType = getIntent().getStringExtra(BOOK_TYPE);
            if(getIntent().getStringExtra(BOOK_TYPE) != null) {
                switch (bookChangeType) {
                    case BOOK_ADD:
                        actionButton.setText("Добавить");
                        actionButton.setOnClickListener(view -> {
                            
                        });
                        break;
                    case BOOK_CHANGE:
                        actionButton.setText("Изменить");
                        actionButton.setOnClickListener(view -> {

                        });
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
