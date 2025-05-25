package com.example.bookbearer.modprofilepic;


import android.net.Uri;

public class ModProfilePicPresenter implements ModProfilePicPres{

    private ModProfilePicView mview;
    private ModProfilePicMod pMod;

    public ModProfilePicPresenter(ModProfilePicView mv){
        mview = mv;
        pMod = new ModProfilePicModel(this);
    }

    @Override
    public String getFileExstension(Uri uri) {
        return mview.getExtension(uri);
    }

    @Override
    public void sendMessage(String msg) {
        mview.displayMessage(msg);
    }

    @Override
    public void uploadImage(Uri imgUri) {
        pMod.uploadImage(imgUri);
    }

    @Override
    public void giveImage(String uriDownload) {
        mview.setProfileImg(uriDownload);
    }

}
