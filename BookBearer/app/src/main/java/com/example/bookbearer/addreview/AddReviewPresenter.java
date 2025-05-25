package com.example.bookbearer.addreview;

import com.example.bookbearer.beans.Review;

public class AddReviewPresenter implements AddReviewPres{

    private AddReviewView arview;
    private AddReviewMod arMod;

    public AddReviewPresenter(AddReviewView arv,boolean test){
        arview = arv;
    }
    public AddReviewPresenter(AddReviewView arv){
        arview = arv;
        arMod = new AddReviewModel(this);
    }

    @Override
    public void sendMessage(String msg) {
        arview.addReviewMessage(msg);
    }

    @Override
    public boolean validateReview(String score, String description) {
        if(description.length()==0){
            arview.addReviewMessage("Descrizione vuota");
            return false;
        }
        if(score.equals("NP")){
            arview.addReviewMessage("Punteggio non selezionato");
            return false;
        }
        return true;
    }


    @Override
    public void addReview(String arISBN,Review r) {
        arMod.addReview(arISBN,r);
    }

    @Override
    public void reviewAdded() {
        arview.reviewAdded();
    }
}
