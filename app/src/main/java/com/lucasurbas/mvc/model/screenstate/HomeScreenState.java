package com.lucasurbas.mvc.model.screenstate;

import com.lucasurbas.mvc.model.ItemModel;
import com.lucasurbas.mvc.model.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 2/27/15.
 */
public class HomeScreenState extends ScreenState {

    public static final String ID = HomeScreenState.class.getCanonicalName();

    private boolean isRefreshing;
    private List<String> itemsIds;

    @Override
    public String getId() {
        return ID;
    }

    public HomeScreenState() {
        super();
        itemsIds = new ArrayList<String>();
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean isRefreshing) {
        this.isRefreshing = isRefreshing;
    }

    public void setItemsIdsList(List<String> itemsIds) {
        this.itemsIds = itemsIds;
    }

    public List<String> getItemsIdsList() {
        return itemsIds;
    }

    @Override
    public <T extends ItemModel> void removeItem(Class<T> clazz, String id) {
        if (clazz.equals(SimpleItem.class)) {
            removeItem(itemsIds, id);
        }
    }

    public void addItemId(int position, String id) {
        itemsIds.add(position, id);
    }
}
