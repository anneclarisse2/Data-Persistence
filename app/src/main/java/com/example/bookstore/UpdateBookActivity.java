package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class UpdateBookActivity extends AppCompatActivity {
    // variables for our edit text, button, strings and dbhandler class.
    private EditText bookTitleEdt, bookYearEdt, bookAuthorEdt;
    private Button updateBookBtn, deleteBookBtn;
    private DBHandler dbHandler;
    String bookTitle, bookYear , bookAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);


        // initializing all our variables.
        bookTitleEdt = findViewById(R.id.idEdtBookTitle);
        bookYearEdt = findViewById(R.id.idEdtBookYear);
        bookAuthorEdt = findViewById(R.id.idEdtBookAuthor);
        updateBookBtn = findViewById(R.id.idBtnUpdateBook);
        deleteBookBtn = findViewById(R.id.idBtnDelete);


        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateBookActivity.this);


        // on below lines we are getting data which
        // we passed in our adapter class.
        bookTitle = getIntent().getStringExtra("title");
        bookYear = getIntent().getStringExtra("year");
        bookAuthor = getIntent().getStringExtra("author");

        // setting data to edit text
        // of our update activity.
        bookTitleEdt.setText(bookTitle);
        bookYearEdt.setText(bookYear);
        bookAuthorEdt.setText(bookAuthor);

        // adding on click listener to our update book button.
        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside this method we are calling an update book
                // method and passing all our edit text values.
                dbHandler.updateBook(bookTitle,bookTitleEdt.getText().toString(),
                        bookYearEdt.getText().toString(), bookAuthorEdt.getText().toString());
                // displaying a toast message that our book has been updated.
                Toast.makeText(UpdateBookActivity.this, "Book Updated..", Toast.LENGTH_SHORT).show();
                // launching our main activity.
                Intent i = new Intent(UpdateBookActivity.this, Home.class);
                startActivity(i);
            }
        });



        // adding on click listener for delete button to delete our book.
        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our book.
                dbHandler.deleteBook(bookTitle);
                Toast.makeText(UpdateBookActivity.this, "Book Successfully Deleted.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateBookActivity.this, Home.class);
                startActivity(i);
            }
        });





    }
}