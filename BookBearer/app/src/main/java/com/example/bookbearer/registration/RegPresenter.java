package com.example.bookbearer.registration;

public interface RegPresenter {

    boolean validateRegistration(String uName,String uMail, String uPass, String uConfPass);
    void registerAccount(String uName,String uMail, String uPass);
    void sendMessage(String msg);
    void sendToLogin();

}
