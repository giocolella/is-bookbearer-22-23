package com.example.bookbearer.modprofilepic;

import android.net.Uri;

public interface ModProfilePicPres {

    String getFileExstension(Uri uri);
    void sendMessage(String msg);
    void uploadImage(Uri imgUri);
    void giveImage(String uriDownload);

}
