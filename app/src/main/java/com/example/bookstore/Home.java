package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Home extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler
    private EditText bookTitleEdt, bookYearEdt, bookAuthorEdt;
    private Button addBookBtn, readBookBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // initializing all our variables.
        bookTitleEdt = findViewById(R.id.idEdtBookTitle);
        bookYearEdt = findViewById(R.id.idEdtYear);
        bookAuthorEdt = findViewById(R.id.idEdtAuthorName);
        addBookBtn = findViewById(R.id.idBtnAddBook);
        readBookBtn = findViewById(R.id.idBtnReadBook);


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(Home.this);
        // below line is to add on click listener for our add book button.
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String bookTitle = bookTitleEdt.getText().toString();
                String bookYear = bookYearEdt.getText().toString();
                String bookAuthor = bookAuthorEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (bookTitle.isEmpty() && bookYear.isEmpty() && bookAuthor.isEmpty()) {
                    Toast.makeText(Home.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // book to sqlite data and pass all our values to it.
                dbHandler.addNewBook(bookTitle, bookYear, bookAuthor);
                // after adding the data we are displaying a toast message.
                Toast.makeText(Home.this, "Book has been added.", Toast.LENGTH_SHORT).show();
                bookTitleEdt.setText("");
                bookYearEdt.setText("");
                bookAuthorEdt.setText("");
            }
        });


        readBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(Home.this, ViewBooks.class);
                startActivity(i);
            }
        });




    }
}