package com.example.bookbearer.login;

public interface LogPresenter {

    boolean validateLogin(String uMail,String uPass);
    void setValidate(boolean val);
    void sendMessage(String msg);
    void loginUser(String uMail,String uPass);
    void signUser();
    void checkCataloghist(String uMail,String uPass);
    void startCatalogue(String uMail);

}
