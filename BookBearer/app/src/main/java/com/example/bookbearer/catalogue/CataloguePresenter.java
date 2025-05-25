package com.example.bookbearer.catalogue;

import android.text.TextUtils;

import com.example.bookbearer.beans.Books;

public class CataloguePresenter implements CataloguePres{

    private CatalogueView cview;
    private CatalogueMod cMod;

    public CataloguePresenter(CatalogueView cv,boolean test){
        cview = cv;
    }
    public CataloguePresenter(CatalogueView cv){
        cview = cv;
        cMod = new CatalogueModel(this);
    }

    @Override
    public void sendMessage(String msg) {
        cview.catalogueMessage(msg);
    }

    //Metodo chiamato per controllare i campi forniti nella view AddCatalogueActivity
    //@Post: Ritorna false se uno qualunque dei campi è la stringa vuota, se ISBN.lenght() < 14, se annoUscita.lenght() < 4
    //oppure non ha match con l'espressione regolare "^[0-9]*$" e se numPagine non ha match con l'espressione regolare "^[0-9]*$".
    //Ritorna true in qualunque altro caso
    @Override
    public boolean validateBook(String ISBN,String titolo, String autore, String genere, String annoUscita, String bookImg, String numPagine) {
        if(ISBN.length() == 0 || titolo.length()==0 || autore.length()==0 || genere.length()==0 || annoUscita.length()==0 || bookImg.length()==0 || numPagine.length()==0){
            cview.catalogueMessage("Potrebbero esserci dei campi mancanti");
            return false;
        }
        if(ISBN.length()<14){
            cview.catalogueMessage("ISBN incorretto");
            return false;
        }
        if(annoUscita.length() < 4 || !annoUscita.matches("^[0-9]*$")){
            cview.catalogueMessage("L'anno è incorretto");
            return false;
        }
        if(!numPagine.matches("^[0-9]*$")){
            cview.catalogueMessage("Il numero delle pagine è incorretto");
            return false;
        }
        return true;
    }

    @Override
    public void addCatBook(Books book) {
        cMod.addCatalogueBook(book);
    }

    @Override
    public void closeActivity() {
        cview.bookAdded();
    }
}
