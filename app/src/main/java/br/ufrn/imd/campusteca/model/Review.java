package br.ufrn.imd.campusteca.model;

import java.io.Serializable;

/**
 * Created by Pablo Gabriel on 23/11/2015.
 */
public class Review implements Serializable {
    private String idUser;
    private String idBook;
    private String description;
    private int rating;

    public Review() {
    }

    public Review(String idUser, String idBook, String description, int rating) {
        this.idUser = idUser;
        this.idBook = idBook;
        this.description = description;
        this.rating = rating;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
