package com.example.bookbearer.reviews;

import com.example.bookbearer.beans.Review;

import java.util.ArrayList;

public class ReviewPresenter implements ReviewPres{

    private ReviewView reviewview;
    private ReviewMod reviewMod;

    public ReviewPresenter(ReviewView rw){
        reviewview = rw;
        reviewMod = new ReviewModel(this);
    }


    @Override
    public void sendMessage(String msg) {
        reviewview.reviewMessage(msg);
    }

    @Override
    public void resultsArrived(ArrayList<Review> results) {
        reviewview.userResults(results);
    }

    @Override
    public void searchReviews(String rISBN) {
        reviewMod.readReviews(rISBN);
    }
}
