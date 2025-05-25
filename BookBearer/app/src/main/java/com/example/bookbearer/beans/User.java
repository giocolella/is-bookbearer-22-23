package com.example.bookbearer.beans;

import java.util.ArrayList;

public class User {

    private String nomeUtente;
    private String email;
    private String password;
    private String imgUrl;
    private ArrayList<String> listaLetti;
    private ArrayList<String> listaDaleggere;

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<String> getListaLetti() {
        return listaLetti;
    }

    public void setListaLetti(ArrayList<String> listaLetti) {
        this.listaLetti = listaLetti;
    }

    public ArrayList<String> getListaDaleggere() {
        return listaDaleggere;
    }

    public void setListaDaleggere(ArrayList<String> listaDaleggere) {
        this.listaDaleggere = listaDaleggere;
    }
}
