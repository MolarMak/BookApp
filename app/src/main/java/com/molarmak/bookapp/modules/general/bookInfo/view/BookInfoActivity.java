package com.molarmak.bookapp.modules.general.bookInfo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.modules.general.bookInfo.presenter.BookInfoPresenter;
import com.molarmak.bookapp.modules.general.bookInfo.presenter.BookInfoPresenterCallback;
import com.molarmak.bookapp.modules.general.main.view.MainActivity;
import com.molarmak.bookapp.storage.Items.Book;

import java.io.ByteArrayOutputStream;

public class BookInfoActivity extends AppCompatActivity implements BookInfoView {

    private ImageView bookImage;
    private EditText bookName, bookAuthor, bookGenre, bookPages;
    private TextView inputError;
    private Button actionButton;

    public static final String BOOK_TYPE = "book_type";
    public static final String BOOK_ADD = "add_book";
    public static final String BOOK_CHANGE = "change_book";
    public static final int GET_FROM_GALLERY = 1;

    private BookInfoPresenterCallback presenter = new BookInfoPresenter();

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
            inputError = findViewById(R.id.inputError);
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

    @Override
    public Book getBookInfo() {
        Book book = new Book();
        try {
            book.setImage(getByteArray());
            book.setName(bookName.getText().toString());
            book.setAuthor(bookAuthor.getText().toString());
            book.setGender(bookGenre.getText().toString());
            book.setPages(getPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void onError(String error) {
        try {
            runOnUiThread(() -> {
                try {
                    inputError.setVisibility(View.VISIBLE);
                    inputError.setText(error);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            onError("Run On Main Thread error");
            e.printStackTrace();
        }
    }

    @Override
    public void onBookAdded() {
        try {
            runOnUiThread(() -> {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            });
        } catch (Exception e) {
            onError("Run On Main Thread error");
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
                            try {
                                inputError.setVisibility(View.INVISIBLE);
                                presenter.startAddBook(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

    private byte[] getByteArray() {
        try {
            BitmapDrawable drawable = (BitmapDrawable) bookImage.getDrawable();
            Bitmap bmp = drawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private int getPages() {
        try {
            int i = Integer.parseInt(bookPages.getText().toString());
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
