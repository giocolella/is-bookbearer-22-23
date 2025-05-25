package com.example.bookbearer.lists;

import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.ListRead;
import com.example.bookbearer.beans.ListToBeRead;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ListsView {

    void listsMessage(String msg);
    void userReadResults(ArrayList<ListRead> l);
    void userToBeReadResults(ArrayList<ListToBeRead> l);

}
