package br.ufrn.imd.campusteca.model;

import java.io.Serializable;

/**
 * Created by Pablo Gabriel on 22/11/2015.
 */
public class Book implements Serializable {
    String author;
    String title;
    String edition;
    String year;
    int quantity;
    int registry;
    int image;

    public Book() {
    }

    public Book(String author, String title, String edition, String year, int quantity, int registry, int image) {
        this.author = author;
        this.title = title;
        this.edition = edition;
        this.year = year;
        this.quantity = quantity;
        this.registry = registry;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRegistry() {
        return registry;
    }

    public void setRegistry(int registry) {
        this.registry = registry;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
