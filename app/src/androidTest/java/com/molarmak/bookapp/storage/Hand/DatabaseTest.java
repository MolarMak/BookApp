package com.molarmak.bookapp.storage.Hand;

import com.molarmak.bookapp.helper.GenerateToken;
import com.molarmak.bookapp.helper.TestHelper;
import com.molarmak.bookapp.storage.Items.Book;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Maxim on 1/21/18.
 */
public class DatabaseTest {

    @Test
    public void saveBookToDatabase() throws Exception {
        //create test book
        Book book = TestHelper.generateTestBook();
        Database database = new Database();
        int numberBe = HelperFactory.getHelper().getBookDAO().getAllBooks().size();
        assertEquals(true, database.saveBookToDatabase(book));  //check that function return result true
        assertEquals(numberBe+1, HelperFactory.getHelper().getBookDAO().getAllBooks().size());  //check that size be +1
    }

    @Test
    public void deleteBookFromDatabase() throws Exception {
        //create test book
        Book book = TestHelper.generateTestBook();
        Database database = new Database();
        int numberBe = HelperFactory.getHelper().getBookDAO().getAllBooks().size();
        assertEquals(true, database.saveBookToDatabase(book));  //check that function return result true
        assertEquals(numberBe+1, HelperFactory.getHelper().getBookDAO().getAllBooks().size());  //check that size be +1
        //test save

        database.deleteBookFromDatabase(book.getToken()); //delete
        assertEquals(numberBe, HelperFactory.getHelper().getBookDAO().getAllBooks().size()); //check that number be previous
    }

    @Test
    public void getBookByToken() throws Exception {
        //create test book
        Book book = TestHelper.generateTestBook();
        Database database = new Database();
        int numberBe = HelperFactory.getHelper().getBookDAO().getAllBooks().size();
        assertEquals(true, database.saveBookToDatabase(book));  //check that function return result true
        assertEquals(numberBe+1, HelperFactory.getHelper().getBookDAO().getAllBooks().size());  //check that size be +1
        //test save

        Book book1 = database.getBookByToken(book.getToken());  //get book by token
        assertEquals(book.getToken(), book1.getToken()); //check tokens equals
    }

    @Test
    public void remakeBookInDatabase() throws Exception {
        //create test book
        Book book = TestHelper.generateTestBook();
        Database database = new Database();
        int numberBe = HelperFactory.getHelper().getBookDAO().getAllBooks().size();
        assertEquals(true, database.saveBookToDatabase(book));  //check that function return result true
        assertEquals(numberBe+1, HelperFactory.getHelper().getBookDAO().getAllBooks().size());  //check that size be +1
        //test save

        book.setName("Remade book from unit tests"); //set new name to book
        database.remakeBookInDatabase(book); //re save
        Book rebook = database.getBookByToken(book.getToken()); //get this book from database
        assertEquals(book.getName(), rebook.getName()); //check than name is equals
    }

}