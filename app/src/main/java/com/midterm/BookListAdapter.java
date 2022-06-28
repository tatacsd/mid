package com.midterm;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.midterm.entity.Book;

public class BookListAdapter extends ListAdapter<Book, BookViewHolder> {
    protected BookListAdapter(@NonNull DiffUtil.ItemCallback<Book> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BookViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book now = getItem(position);
        holder.bind(now.getName(),now.getAuthors(), now.getPrice());
    }


    public static class BookDiff extends DiffUtil.ItemCallback<Book> {

        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getAuthors().equals(newItem.getAuthors()) &&
                    oldItem.getPrice() == newItem.getPrice();
        }
    }
}
