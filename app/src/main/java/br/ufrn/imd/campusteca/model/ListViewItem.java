package br.ufrn.imd.campusteca.model;

/**
 * Created by Pablo Gabriel on 07/10/2015.
 */
public class ListViewItem {
    private String title;
    private int image;

    public ListViewItem() {

    }

    public ListViewItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
