package com.example.bookbearer.search;


import android.util.Log;

import com.example.bookbearer.beans.LightBook;

import java.util.ArrayList;
import java.util.Map;

public class SearchPresenter implements SearchPres{

    private SearchView sview;
    private SearchMod sModel;

    public SearchPresenter(SearchView l){
        sview = l;
        sModel = new SearchModel(this);
    }

    @Override
    public ArrayList<LightBook> getSearched(String title) {
        return sModel.giveSearched(title);
    }

    @Override
    public void resultArrived(ArrayList<LightBook> lb) {
        if(lb.size() == 0){
            sview.searchMessage("Nessun risultato");
        }
        sview.useResult(lb);
    }
}
