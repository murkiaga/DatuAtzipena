package com.DatuAtzipena.app.modeloak;


public class Erabiltzailea {
    private int id;           
    private String izena;     
    private String abizena;   
    private String emaila;    

    public Erabiltzailea(int id, String izena, String abizena, String emaila) {
        this.id = id;
        this.izena = izena;
        this.abizena = abizena;
        this.emaila = emaila;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena() {
        return abizena;
    }

    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    public String getEmaila() {
        return emaila;
    }

    public void setEmaila(String emaila) {
        this.emaila = emaila;
    }
}
