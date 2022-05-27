package com.example.bookstore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewBooks extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<BookModal> bookModalArrayList;
    private DBHandler dbHandler;
    private BookAdapter  bookRVAdapter;
    private RecyclerView  booksRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);



        // initializing our all variables.
        bookModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewBooks.this);

        // getting our book] array
        // list from db handler class.
        bookModalArrayList = dbHandler.readBooks();

        // on below line passing our array lost to our adapter class.
        bookRVAdapter = new BookAdapter(bookModalArrayList, ViewBooks.this);
        booksRV = findViewById(R.id.idRVBooks);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewBooks.this, RecyclerView.VERTICAL, false);
        booksRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        booksRV.setAdapter(bookRVAdapter);
    }
}
