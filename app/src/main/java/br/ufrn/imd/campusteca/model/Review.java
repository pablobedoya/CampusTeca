package br.ufrn.imd.campusteca.model;

import java.io.Serializable;

/**
 * Created by Pablo Gabriel on 23/11/2015.
 */
public class Review implements Serializable {
    private int idBook;
    private String username;
    private float rating;
    private String description;

    public Review() {
    }

    public Review(int idBook, String username, String description, int rating) {
        this.idBook = idBook;
        this.username = username;
        this.rating = rating;
        this.description = description;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
