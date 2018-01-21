package com.molarmak.bookapp.modules.general.bookInfo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
    public static final int GET_FROM_GALLERY = 1;

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
            bookImage.setOnClickListener(view ->
                    startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                bookImage.setImageBitmap(scaled);
            }
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
