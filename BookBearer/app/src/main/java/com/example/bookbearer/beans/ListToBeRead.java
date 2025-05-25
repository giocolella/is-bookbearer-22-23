package com.example.bookbearer.beans;

import java.util.List;

public class ListToBeRead {
    private List<String> toBeReadBooks;
    private String ISBN;
    private String bookImg;
    private String title;
    private String author;

    public ListToBeRead(){

    }

    public ListToBeRead(List<String> toBeReadBooks, String bookImg, String title, String author) {
        this.toBeReadBooks = toBeReadBooks;
        this.bookImg = bookImg;
        this.title = title;
        this.author = author;
    }

    public List<String> getToBeReadBooks() {
        return toBeReadBooks;
    }

    public void setToBeReadBooks(List<String> toBeReadBooks) {
        this.toBeReadBooks = toBeReadBooks;
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
