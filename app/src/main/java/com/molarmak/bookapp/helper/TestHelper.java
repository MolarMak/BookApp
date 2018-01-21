package com.molarmak.bookapp.helper;

import com.molarmak.bookapp.storage.Items.Book;

/**
 * Created by Maxim on 1/21/18.
 */

public class TestHelper {
    public static Book generateTestBook() {
        Book book = new Book();
        book.setToken(GenerateToken.generate());
        book.setImage(new byte[10]); //without image
        book.setName("Алиса в стране чудес [Tested from unit]");
        book.setAuthor("Льюис Кэрролл");
        book.setGenre("Фантастика");
        book.setPages(450);
        return book;
    }
}
