package com.example.bookbearer.login;

import android.text.TextUtils;
import android.widget.Toast;

public class LoginPresenter implements LogPresenter{

    private LogView lview;
    private LogModel lModel;
    private boolean validate = false;

    public LoginPresenter(LogView l,boolean test){
        lview=l;
    }
    public LoginPresenter(LogView l){
        lview = l;
        lModel = new LoginModel(this);
    }

    @Override
    public boolean validateLogin(String uMail, String uPass) {
        if(uMail.length()==0 || uPass.length()==0){
            lview.regMessage("Potrebbe mancare qualche campo");
            return false;
        }
        return true;
    }

    @Override
    public void setValidate(boolean val) {
            validate = val;
    }

    @Override
    public void sendMessage(String msg) {
        lview.regMessage(msg);
    }

    @Override
    public void loginUser(String uMail, String uPass) {
        lModel.loginUser(uMail,uPass);
    }

    @Override
    public void signUser() {
        lview.StartActivity();
    }

    @Override
    public void checkCataloghist(String uMail, String uPass) {
        lModel.isCataloghist(uMail,uPass);
    }

    @Override
    public void startCatalogue(String uMail) {
        lview.startCatalogueActivity(uMail);
    }
}
