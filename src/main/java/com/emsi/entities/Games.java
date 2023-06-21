package com.emsi.entities;

import java.io.Serializable;
import java.util.Objects;

public class Games implements Serializable {


    private int id;
    private String name;
    private String plateforme;
    private double prix;
    private int note;
    private String type;
    private String developpeur;

    public Games() {
    }

    public Games(int id, String name , String plateforme, double prix , int note , String type , String developpeur) {
        this.id = id;
        this.name = name;
        this.plateforme = plateforme;
        this.prix = prix;
        this.note = note;
        this.type = type;
        this.developpeur = developpeur;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getPlateforme() {
        return plateforme;
    }

    public double getPrix() {
        return prix;
    }

    public int getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getDeveloppeur() {
        return developpeur;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plateforme='" + plateforme + '\'' +
                ", prix='" + prix + '\'' +
                ", note='" + note + '\'' +
                ", type=" + type +
                ", developpeur='" + developpeur + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games that = (Games) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(plateforme, that.plateforme) && Objects.equals(prix, that.prix) && Objects.equals(note, that.note) && Objects.equals(type, that.type) && Objects.equals(developpeur, that.developpeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, plateforme, prix, note, type, developpeur);
    }
}
