package com.example.bookbearer.bookinfo;

import java.util.Map;

public class BookInfoPresenter implements BookInfoPres{

    private BookInfoView bView;
    private BookInfoMod bM;

    public BookInfoPresenter(BookInfoView v){
        bView = v;
        bM = new BookInfoModel(this);
    }


    @Override
    public void sendMessage(String msg) {
        bView.bookInfoMessage(msg);
    }

    @Override
    public void searchBookInfo(String ISBN) {
        bM.getSelectedBook(ISBN);
    }

    @Override
    public void bookArrived(Map<String, Object> mp) {
        bView.useBookResult(mp);
    }

    @Override
    public void addToTheToBeRead(String ISBN) {
        bM.addToList(ISBN);
    }

    @Override
    public void checkReview(String ISBN) {
        bM.checkIfSecond(ISBN);
    }

    @Override
    public void sendToAR() {
        bView.sendToAddReview();
    }

    @Override
    public void deleteReview(String ISBN) {
        bM.deleteReviewDocument(ISBN);
    }
}
