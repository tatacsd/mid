package com.midterm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.midterm.db.BookRepository;
import com.midterm.entity.Book;
import java.util.List;


public class BookViewModel extends AndroidViewModel {

    private BookRepository mRepository;

    private LiveData<List<Book>> mAllBooks;

    public BookViewModel (Application application) {
        super(application);
        mRepository = new BookRepository(application);

        mAllBooks = mRepository.getAllBooks();
    }

    public void insert(Book book) { mRepository.insert(book); }

    public void update(Book book) {
        mRepository.update(book);
    }

     // check if book is in the database, if not, return null
    public Book checkBook(String bookTitle, String bookAuthor) {
        return mRepository.checkBook(bookTitle, bookAuthor);
    }

    public LiveData<List<Book>> getAllBooks() { return mAllBooks; }
}
