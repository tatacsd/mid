package com.midterm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.midterm.entity.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Book book);

    @Query("DELETE FROM book_table")
    void deleteAll();

    @Update
    void update(Book book);

    @Query("SELECT * FROM book_table ORDER BY name ASC")
    LiveData<List<Book>> getAlphabetizedBooks();

    // if book is in the database, return the book, otherwise return null
    @Query("SELECT * FROM book_table WHERE name = :name")
    Book checkBook(String name);

}
