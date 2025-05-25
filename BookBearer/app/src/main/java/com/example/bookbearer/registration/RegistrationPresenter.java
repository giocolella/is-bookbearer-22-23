package com.example.bookbearer.registration;

import android.text.TextUtils;

public class RegistrationPresenter implements RegPresenter{

    private RegView regView;
    private RegModel regM;

    private boolean validate = false;

    public RegistrationPresenter(RegView v,boolean test){
        regView = v;
    }
    public RegistrationPresenter(RegView v){
        regView = v;
        regM = new RegistrationModel(this);
    }

    @Override
    public boolean validateRegistration(String uName,String uMail, String uPass, String uConfPass) {
        if(uName.length()==0 || uMail.length()==0 || uPass.length()==0 || uConfPass.length()==0){
            regView.regMessage("Potrebbero esserci dei campi vuoti");
            return false;
        }
        if(!uMail.contains("@")){
            regView.regMessage("Email incompleta");
            return false;
        }
        if(!uPass.equals(uConfPass)){
            regView.regMessage("Password e Conferma password non coincidono");
            return false;
        }
        if(uPass.length() < 8){
            regView.regMessage("Password troppo corta, almeno 8 caratteri");
            return false;
        }
        return true;
    }

    @Override
    public void registerAccount(String uName,String uMail, String uPass) {
        regM.registerUser(uName,uMail,uPass);
    }


    @Override
    public void sendMessage(String msg) {
        regView.regMessage(msg);
    }

    @Override
    public void sendToLogin() {
        regView.goToLogin();
    }
}
