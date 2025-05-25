package com.example.bookbearer.reviews;

import com.example.bookbearer.beans.Review;

import java.util.ArrayList;

public interface ReviewView {

    void reviewMessage(String msg);
    void userResults(ArrayList<Review> results);

}
