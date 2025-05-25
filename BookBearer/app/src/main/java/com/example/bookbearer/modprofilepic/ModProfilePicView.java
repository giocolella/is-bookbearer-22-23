package com.example.bookbearer.modprofilepic;

import android.net.Uri;

public interface ModProfilePicView {

    void displayMessage(String msg);
    String getExtension(Uri uri);
    void setProfileImg(String uri);

}
