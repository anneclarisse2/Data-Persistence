package com.example.bookstore;

public class BookModal {

    // variables for our title
    // year and author.
    private String bookTitle;
    private String bookYear;
    private String bookAuthor;
    private int id;

    // creating getter and setter methods
    public String getbookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getbookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public String getbookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public BookModal(String bookTitle, String bookYear, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookYear = bookYear;
        this.bookAuthor = bookAuthor;
    }
}
