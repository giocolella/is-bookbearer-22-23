package com.example.bookbearer.beans;

import java.util.List;

public class ListRead {
    private List<String> readBooks;
    private String ISBN;
    private String bookImg;
    private String title;
    private String author;

    public ListRead(){

    }

    public ListRead(List<String> readBooks, String bookImg, String title, String author) {
        this.readBooks = readBooks;
        this.bookImg = bookImg;
        this.title = title;
        this.author = author;
    }

    public List<String> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(List<String> readBooks) {
        this.readBooks = readBooks;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
