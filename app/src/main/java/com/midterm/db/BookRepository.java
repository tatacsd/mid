package com.midterm.db;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.midterm.dao.BookDao;
import com.midterm.entity.Book;

import java.util.List;

public class BookRepository {
    public BookDao mBookDao;
    public LiveData<List<Book>> mAllBooks;

    public BookRepository(Application application) {
        BookRoomDatabase database = BookRoomDatabase.getDatabase(application);
        mBookDao = database.bookDao();

        mAllBooks = (LiveData<List<Book>>) mBookDao.getAlphabetizedBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }

    public void insert(Book book) {
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.insert(book);
        });
    }

    public Book checkBook(String bookTitle, String bookAuthor) {
        if (mBookDao.checkBook(bookTitle) != null) {
            // get book from database
            Book book = mBookDao.checkBook(bookTitle);
            Log.d("BookRepository", "checkBook: " + book.getName() + " " + book.getAuthors());
            // check if book has the same author
            if (book.getAuthors().equals(bookAuthor)) {
                return book;
            } else{
                return null;
            }
        }
        return null;
    }

    public void update(Book book) {
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.update(book);
        });
    }

}
