package com.molarmak.bookapp.storage.Items;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Maxim on 1/20/18.
 */
@DatabaseTable(tableName = Book.TABLE_NAME_BOOKS)
public class Book {

    public static final String TABLE_NAME_BOOKS = "books";

    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_IMAGE = "image";
    private static final String FIELD_NAME_BOOK_NAME = "book_name";
    private static final String FIELD_NAME_AUTHOR = "author";
    private static final String FIELD_NAME_GENDER = "gender";
    private static final String FIELD_NAME_PAGES = "pages";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_IMAGE, dataType = DataType.BYTE_ARRAY)
    private byte[] image;

    @DatabaseField(columnName = FIELD_NAME_BOOK_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_AUTHOR)
    private String author;

    @DatabaseField(columnName = FIELD_NAME_GENDER)
    private String gender;

    @DatabaseField(columnName = FIELD_NAME_PAGES)
    private int pages;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
