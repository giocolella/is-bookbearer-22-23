package com.example.bookbearer.modmail;

public interface ModMailPres {

    boolean validateFields(String uMail,String uPass);
    void reAutenticate(String uMail,String uPass);
    void sendMessage(String msg);
    void finished();

}
