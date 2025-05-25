package com.example.bookbearer.addreview;

import com.example.bookbearer.beans.Review;

public interface AddReviewPres {

    void sendMessage(String msg);
    public boolean validateReview(String score,String description);
    void addReview(String arISBN,Review r);
    void reviewAdded();

}
