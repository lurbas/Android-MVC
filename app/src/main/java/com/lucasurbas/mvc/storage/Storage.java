package com.lucasurbas.mvc.storage;

import android.support.annotation.Nullable;

import com.lucasurbas.mvc.model.ItemModel;
import com.lucasurbas.mvc.model.screenstate.ScreenState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lucas on 2/27/15.
 */
public class Storage {

    private static Storage instance;
    private TablesHelper tablesHelper;

    private ConcurrentHashMap<String, ScreenState> screens;

    public static Storage getInstance() {
        if (instance == null) {
            synchronized (Storage.class) {
                if (instance == null) {
                    instance = new Storage();
                }
            }
        }
        return instance;
    }

    private Storage() {
        tablesHelper = new TablesHelper();
        screens = new ConcurrentHashMap<String, ScreenState>();
    }

    @Nullable
    public ScreenState getScreenStateWithId(String id) {
        return screens.get(id);
    }

    public ScreenState getOrCreateScreenState(ScreenState screenState) {
        synchronized (screenState.getClass()) {
            if (screens.containsKey(screenState.getId())) {
                return screens.get(screenState.getId());
            } else {
                screens.put(screenState.getId(), screenState);
                return screenState;
            }
        }
    }

    public void deleteScreenStateWithId(String id) {
        screens.remove(id);
    }

    public <T extends ItemModel> T getItemWithId(Class<T> clazz, String id) {
        ConcurrentHashMap<String, T> table = tablesHelper.getTable(clazz);
        return table.get(id);
    }

    public <T extends ItemModel> void createOrUpdateItem(Class<T> clazz, final T item) {
        ConcurrentHashMap<String, T> table = tablesHelper.getTable(clazz);
        synchronized (clazz) {
            if (table.containsKey(item.getId())) {
                ItemModel oldItem = table.get(item.getId());
                item.updateScreensStates(oldItem);
            }
            table.put(item.getId(), item);
            item.notifyDataChanged();
        }
    }

    public <T extends ItemModel> void deleteItemWithId(Class<T> clazz, String id) {
        ConcurrentHashMap<String, T> table = tablesHelper.getTable(clazz);
        synchronized (clazz) {
            if (table.containsKey(id)) {
                ItemModel itemToRemove = table.get(id);
                itemToRemove.onRemove();
                table.remove(id);
            }
        }
    }

    public <T extends ItemModel> List<T> getAll(Class<T> clazz) {
        ConcurrentHashMap<String, T> table = tablesHelper.getTable(clazz);
        return table != null ? new ArrayList(table.values()) : new ArrayList<T>();
    }
}
