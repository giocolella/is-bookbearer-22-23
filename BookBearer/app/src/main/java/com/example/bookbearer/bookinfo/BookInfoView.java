package com.example.bookbearer.bookinfo;

import java.util.Map;

public interface BookInfoView {

    void bookInfoMessage(String msg);

    void useBookResult(Map<String, Object> mp);
    void sendToAddReview();

}
