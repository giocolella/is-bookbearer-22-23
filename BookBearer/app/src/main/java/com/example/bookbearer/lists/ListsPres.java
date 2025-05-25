package com.example.bookbearer.lists;

import com.example.bookbearer.beans.LightBook;

import java.util.List;
import java.util.Map;

public interface ListsPres {

    void readSelected();
    void toBeReadSelected();
    void sendMessage(String msg);
    void giveReadResults(List<Map<String,Object>> l);
    void giveToBeReadResults(List<Map<String,Object>> l);

}
