package com.lucasurbas.mvc.model.screenstate;

import com.lucasurbas.mvc.model.ItemModel;
import com.lucasurbas.mvc.model.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 3/1/15.
 */
public class FavoritesScreenState extends ScreenState  {

    public static final String ID = FavoritesScreenState.class.getCanonicalName();

    private List<String> itemsIds;

    @Override
    public String getId() {
        return ID;
    }

    public FavoritesScreenState() {
        super();
        itemsIds = new ArrayList<String>();
    }

    public void setItemsIdsList(List<String> itemsIds) {
        this.itemsIds = itemsIds;
    }

    public List<String> getItemsIdsList() {
        return itemsIds;
    }

    public void addItemId(String id) {
        itemsIds.add(id);
    }

    public void removeItemId(String id) {
        itemsIds.remove(id);
    }

    @Override
    public <T extends ItemModel> void removeItem(Class<T> clazz, String id) {
        if (clazz.equals(SimpleItem.class)) {
            removeItem(itemsIds, id);
        }
    }
}
