package com.lucasurbas.mvc.storage;


import com.lucasurbas.mvc.model.ItemModel;
import com.lucasurbas.mvc.model.SimpleItem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lucas on 2/27/15.
 */
public class TablesHelper {

    private Map<Class<?>, ConcurrentHashMap<String, ?>> tables;

    public TablesHelper() {
        tables = new HashMap<Class<?>, ConcurrentHashMap<String, ?>>();
        createTables();
    }

    private <T extends ItemModel> void addTable(Class<T> clazz) {

        ConcurrentHashMap<String, T> table = new ConcurrentHashMap<String, T>();
        tables.put(clazz, table);

    }

    private void createTables() {

        addTable(SimpleItem.class);
    }

    public void reset() {
        tables.clear();
        createTables();
    }

    public <T extends ItemModel> ConcurrentHashMap<String, T> getTable(
            Class<T> clazz) {

        ConcurrentHashMap<String, ?> table = tables.get(clazz);
        if (table != null) {
            return (ConcurrentHashMap<String, T>) table;
        }
        throw new IllegalArgumentException("Table: " + clazz.getSimpleName() + " not created");
    }
}
