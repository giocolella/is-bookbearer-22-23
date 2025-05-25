package com.example.bookbearer.lists;

import com.example.bookbearer.beans.LightBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ListsMod {

    void getRead();
    void getToBeRead();
    void checkLists();
    void resultsReadArrived(List<Map<String,Object>> l);
    void resultsToBeReadArrived(List<Map<String,Object>> l);
    void addRead(Map<String,Object> m);
    void deleteRead(Map<String,Object> m);
    void deleteToBeRead(Map<String,Object> m);

}
