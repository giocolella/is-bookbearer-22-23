package com.example.bookbearer.search;

import com.example.bookbearer.beans.LightBook;

import java.util.ArrayList;
import java.util.Map;

public interface SearchMod {

    ArrayList<LightBook> giveSearched(String title);

}
