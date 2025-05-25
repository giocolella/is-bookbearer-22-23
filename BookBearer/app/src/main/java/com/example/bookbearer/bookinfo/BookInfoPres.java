package com.example.bookbearer.bookinfo;

import java.util.Map;

public interface BookInfoPres {

    void sendMessage(String msg);
    void searchBookInfo(String ISBN);
    void bookArrived(Map<String, Object> mp);
    void addToTheToBeRead(String ISBN);
    void checkReview(String ISBN);
    void sendToAR();
    void deleteReview(String ISBN);

}
