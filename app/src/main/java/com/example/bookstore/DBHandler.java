package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "bookdb";
    // below is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "mybookdb";
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for our book name column
    private static final String TITLE_COL = "title";
    // below variable id for our book duration column.
    private static final String YEAR_COL = "year";
    // below variable for our book description column.
    private static final String AUTHOR_COL = "author";
    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
    // on below line we are creating
    // an sqlite query and we are
    // setting our column names
    // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + YEAR_COL + " TEXT,"
                + AUTHOR_COL + " TEXT)";
    // at last we are calling a exec sql
    // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new book to our sqlite database.
    public void addNewBook(String bookTitle, String bookYear, String bookAuthor) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TITLE_COL, bookTitle);
        values.put(YEAR_COL, bookYear);
        values.put(AUTHOR_COL, bookAuthor);
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }


    // we have created a new method for reading all the book.
    public ArrayList<BookModal> readBooks() {
        // on below line we are creating database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<BookModal> bookModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorBooks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                bookModalArrayList.add(new BookModal(cursorBooks.getString(1),
                        cursorBooks.getString(2),
                        cursorBooks.getString(3)));
            } while (cursorBooks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorBooks.close();
        return bookModalArrayList;
    }


    // below is the method for updating our books
    public void updateBook(String originalBookTitle, String bookTitle, String bookYear,
                             String bookAuthor) {
         // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TITLE_COL, bookTitle);
        values.put(YEAR_COL, bookYear);
        values.put(AUTHOR_COL, bookAuthor);
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our books which is stored in original name variable.
        db.update(TABLE_NAME, values, "title=?", new String[]{originalBookTitle});
        db.close();
    }


    // below is the method for deleting our book.
    public void deleteBook(String bookTitle) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // book and we are comparing it with our book name.
        db.delete(TABLE_NAME, "title=?", new String[]{bookTitle});
        db.close();
    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
