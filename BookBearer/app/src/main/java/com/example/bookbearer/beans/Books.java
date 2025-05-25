package com.example.bookbearer.beans;

import java.util.ArrayList;

public class Books {

    private String isbn;
    private String titolo;
    private String autore;
    private String bookImg;
    private String data;
    private String genere;
    private String numPagine;
    private long puntThebearers;
    private ArrayList<Review> reviewArray;

    public Books(String isbn, String titolo, String autore,String genere,String data,String bookImg, String numPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.data = data;
        this.bookImg= bookImg;
        this.genere = genere;
        this.numPagine = numPagine;
    }

    public ArrayList<Review> getReviewArray() {
        return reviewArray;
    }

    public void setReviewArray(ArrayList<Review> reviewArray) {
        this.reviewArray = reviewArray;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getNumPagine() {
        return numPagine;
    }

    public void setNumPagine(String numPagine) {
        this.numPagine = numPagine;
    }

    public long getPuntThebearers() {
        return puntThebearers;
    }

    public void setPuntThebearers(long puntThebearers) {
        this.puntThebearers = puntThebearers;
    }

}
