package com.example.bookbearer.beans;

public class Review {

    private String punteggio;
    private String autore;
    private String descrizione;

    public Review(){

    }

    public Review(String punteggio, String autore, String descrizione) {
        this.punteggio = punteggio;
        this.autore = autore;
        this.descrizione = descrizione;
    }

    public String getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(String punteggio) {
        this.punteggio = punteggio;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
