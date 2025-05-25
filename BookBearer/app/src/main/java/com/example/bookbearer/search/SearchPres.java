package com.example.bookbearer.search;

import com.example.bookbearer.beans.LightBook;

import java.util.ArrayList;
import java.util.Map;

public interface SearchPres {

    ArrayList<LightBook> getSearched(String title);
    void resultArrived(ArrayList<LightBook> lb);

}
