package com.lucasurbas.mvc.controller;

import com.lucasurbas.mvc.model.SimpleItem;
import com.lucasurbas.mvc.model.screenstate.FavoritesScreenState;
import com.lucasurbas.mvc.storage.Storage;

/**
 * Created by Lucas on 3/1/15.
 */
public class SimpleItemController {

    public static void toggleFavorite(final SimpleItem simpleItem) {

        FavoritesScreenState screenState = (FavoritesScreenState) Storage.getInstance().getOrCreateScreenState(new FavoritesScreenState());
        if (simpleItem.isFavorite()) {
            screenState.removeItemId(simpleItem.getId());
            simpleItem.removeScreenState(screenState);
        } else {
            screenState.addItemId(simpleItem.getId());
            simpleItem.addScreenState(screenState);
        }
        simpleItem.setFavorite(!simpleItem.isFavorite());
        Storage.getInstance().createOrUpdateItem(SimpleItem.class, simpleItem);

        screenState.setStateChanged();
    }

    public static void delete(SimpleItem simpleItem) {
        Storage.getInstance().deleteItemWithId(SimpleItem.class, simpleItem.getId());
    }
}
