package com.lucasurbas.mvc.model;

/**
 * Created by Lucas on 2/27/15.
 */
public class SimpleItem extends ItemModel {

    private String id;
    private String content;
    private boolean isFavorite;

    @Override
    public String getId() {
        return id;
    }

    public SimpleItem(String id) {
        super();
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
