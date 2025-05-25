package com.example.bookbearer.bookinfo;

public interface BookInfoMod {

    void getSelectedBook(String ISBN);
    void addToList(String ISBN);
    boolean checkIfSecond(String ISBN);
    void deleteReviewDocument(String ISBN);

}
