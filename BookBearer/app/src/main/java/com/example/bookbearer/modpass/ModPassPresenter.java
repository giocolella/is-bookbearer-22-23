package com.example.bookbearer.modpass;

import android.text.TextUtils;

public class ModPassPresenter implements ModPassPres{

    private ModPassView pview;
    private ModPassMod pMod;

    public ModPassPresenter(ModPassView v,boolean test){
        pview=v;
    }

    public ModPassPresenter(ModPassView v){
        pview = v;
        pMod = new ModPassModel(this);
    }

    @Override
    public boolean validateFields(String uNPass, String uOPass) {
        if(TextUtils.isEmpty(uNPass) || TextUtils.isEmpty(uOPass)){
            pview.modMessage("Potrebbero esserci dei campi vuoti");
            return false;
        }
        if(uNPass.length() < 8){
            pview.modMessage("Password troppo corta, almeno 8 caratteri");
            return false;
        }
        if(uOPass.length() < 8){
            pview.modMessage("Password troppo corta, almeno 8 caratteri");
            return false;
        }
        return true;
    }

    @Override
    public void reAutenticate(String uNPass, String uOPass) {
        pMod.reAutenticateAndChangePass(uNPass,uOPass);
    }

    @Override
    public void sendMessage(String msg) {
        pview.modMessage(msg);
    }

    @Override
    public void finished() {
        pview.finishActivity();
    }

}
