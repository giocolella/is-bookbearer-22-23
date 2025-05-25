package com.example.bookbearer.modmail;

import android.text.TextUtils;

public class ModMailPresenter implements ModMailPres{

    private ModMailView mview;
    private ModMailMod eMod;


    public ModMailPresenter(ModMailView v,boolean test){
        mview=v;
    }

    public ModMailPresenter(ModMailView v){
        mview = v;
        eMod = new ModMailModel(this);
    }

    @Override
    public boolean validateFields(String uMail, String uPass) {
        if(TextUtils.isEmpty(uMail) || TextUtils.isEmpty(uPass)){
            mview.modMessage("Potrebbero esserci dei campi vuoti");
            return false;
        }
        if(!uMail.contains("@")){
            mview.modMessage("Email incompleta");
            return false;
        }
        if(uPass.length() < 8){
            mview.modMessage("Password troppo corta, almeno 8 caratteri");
            return false;
        }
        return true;
    }

    @Override
    public void reAutenticate(String uMail, String uPass) {
        eMod.reAutenticateAndChangeMail(uMail,uPass);
    }

    @Override
    public void sendMessage(String msg) {
        mview.modMessage(msg);
    }

    @Override
    public void finished() {
        mview.finishActivity();
    }
}
