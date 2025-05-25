package com.example.bookbearer.catalogue;

import com.example.bookbearer.beans.Books;

public interface CataloguePres {

    void sendMessage(String msg);
    boolean validateBook(String ISBN,String titolo,String autore,String genere,String annoUscita,String bookImg,String numPagine);
    void addCatBook(Books book);
    void closeActivity();

}
