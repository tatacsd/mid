package com.midterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midterm.entity.Book;

public class MainActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;

    private Button mUpdate;
    private EditText mTitle;
    private EditText mAuthors;
    private EditText mPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = findViewById(R.id.txtFragTitle);
        mAuthors = findViewById(R.id.txtFragAuthors);
        mPrice = findViewById(R.id.txtFragPrice);

        mUpdate = findViewById(R.id.btnUpdate);

        RecyclerView recyclerView = findViewById(R.id.bookList);
        final BookListAdapter adapter = new BookListAdapter(new BookListAdapter.BookDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        bookViewModel.getAllBooks().observe(this, books -> {adapter.submitList(books);});

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(mTitle.getText()) && !TextUtils.isEmpty(mAuthors.getText()) && !TextUtils.isEmpty(mPrice.getText())){
                    String title, author, price;
                    title = mTitle.getText().toString();
                    author = mAuthors.getText().toString();
                    price = mPrice.getText().toString();

                    Book newBook = new Book(title, author, Double.parseDouble(price));
                    // check if book title already exists in the database
                    if(bookViewModel.checkBook(title, author) != null){
                        bookViewModel.update(newBook);
                        Toast.makeText(MainActivity.this, "Book updated", Toast.LENGTH_SHORT).show();
                    }else{
                        bookViewModel.insert(newBook);
                        Toast.makeText(MainActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}