package com.example.bookbearer.modpass;

public interface ModPassPres {

    boolean validateFields(String uNPass,String uOPass);
    void reAutenticate(String uNPass,String uOPass);
    void sendMessage(String msg);
    void finished();

}
