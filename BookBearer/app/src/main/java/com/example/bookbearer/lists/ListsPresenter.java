package com.example.bookbearer.lists;

import com.example.bookbearer.beans.LightBook;
import com.example.bookbearer.beans.ListRead;
import com.example.bookbearer.beans.ListToBeRead;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListsPresenter implements ListsPres{

    private ListsView lview;
    private ListsMod lMod;
    private ListRead lRead = new ListRead();
    private ListToBeRead lToBeRead = new ListToBeRead();
    private ArrayList<ListRead> arrayRead = new ArrayList<>();
    private ArrayList<ListToBeRead> arrayToBeRead = new ArrayList<>();

    public ListsPresenter(ListsView lv){
        lview = lv;
        lMod = new ListsModel(this);
    }

    @Override
    public void readSelected() {
        lMod.getRead();
    }

    @Override
    public void toBeReadSelected() {
        lMod.getToBeRead();
    }

    @Override
    public void sendMessage(String msg) {
        lview.listsMessage(msg);
    }

    //Fornisce a ListReadAdapter i libri della lista letti con cui popolare la ListView
    //@Post: Aggiunge i libri, se presenti, al ListView.
    @Override
    public void giveReadResults(List<Map<String,Object>> l) {
        for(Map<String,Object> m : l){
            lRead.setAuthor(m.get("autore").toString());
            lRead.setTitle(m.get("titolo").toString());
            lRead.setBookImg(m.get("bookImg").toString());
            lRead.setISBN(m.get("ISBN").toString());
            arrayRead.add(lRead);
            lRead = new ListRead();
        }
        lview.userReadResults(arrayRead);
        arrayRead = new ArrayList<>();
    }

    //Fornisce a ListReadAdapter i libri della lista da leggere con cui popolare la ListView
    //@Post: Aggiunge i libri, se presenti, al ListView.
    @Override
    public void giveToBeReadResults(List<Map<String, Object>> l) {
        for(Map<String,Object> m : l){
            lToBeRead.setAuthor(m.get("autore").toString());
            lToBeRead.setTitle(m.get("titolo").toString());
            lToBeRead.setBookImg(m.get("bookImg").toString());
            lToBeRead.setISBN(m.get("ISBN").toString());
            arrayToBeRead.add(lToBeRead);
            lToBeRead = new ListToBeRead();
        }
        lview.userToBeReadResults(arrayToBeRead);
        arrayToBeRead = new ArrayList<>();
    }


}
