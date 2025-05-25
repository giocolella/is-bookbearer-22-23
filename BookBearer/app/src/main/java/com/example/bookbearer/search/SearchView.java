package com.example.bookbearer.search;

import com.example.bookbearer.beans.LightBook;

import java.util.ArrayList;

public interface SearchView {

    void searchMessage(String msg);
    void useResult(ArrayList<LightBook> lb);


}
