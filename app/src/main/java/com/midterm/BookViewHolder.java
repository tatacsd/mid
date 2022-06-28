package com.midterm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder{
    private final TextView titleItemView;
    private final TextView authorsItemView;
    private final TextView priceItemView;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        titleItemView = itemView.findViewById(R.id.txtTitle);
        authorsItemView = itemView.findViewById(R.id.txtAuthors);
        priceItemView = itemView.findViewById(R.id.txtPrice);
    }

    public void bind (String title, String authors, double price) {
        titleItemView.setText(title);
        authorsItemView.setText(authors);
        priceItemView.setText(String.valueOf(price));
    }

    public static BookViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        return new BookViewHolder(view);
    }

}
