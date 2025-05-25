package com.example.bookbearer.reviews;

import com.example.bookbearer.beans.Review;

import java.util.ArrayList;

public interface ReviewPres {

    void sendMessage(String msg);
    void resultsArrived(ArrayList<Review> results);
    void searchReviews(String rISBN);
}
