package com.midterm.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {

    // Columns
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "authors")
    private String mAuthors;

    @ColumnInfo(name = "price")
    private double mPrice;
    public Book(String name, String authors, double price) {
        this.mName = name;
        this.mAuthors = authors;
        this.mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public void setAuthors(String mAuthors) {
        this.mAuthors = mAuthors;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }
}
