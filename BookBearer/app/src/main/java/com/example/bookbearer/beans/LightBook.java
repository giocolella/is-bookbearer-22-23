package com.example.bookbearer.beans;

public class LightBook {
    private String title;
    private String author;
    private String isbn;
    String imageUrl;

    public LightBook(){}

    public LightBook(String title, String author, String isbn, String iUrl) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.imageUrl = iUrl;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
