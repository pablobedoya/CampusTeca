package br.ufrn.imd.campusteca.model;

/**
 * Created by Pablo Gabriel on 22/11/2015.
 */
public class Book {
    String author;
    String title;
    String edition;
    String year;
    int quantity;
    int registration;
    int image;

    public Book() {
    }

    public Book(String author, String title, String edition, String year, int quantity, int registration, int image) {
        this.author = author;
        this.title = title;
        this.edition = edition;
        this.year = year;
        this.quantity = quantity;
        this.registration = registration;
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

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
