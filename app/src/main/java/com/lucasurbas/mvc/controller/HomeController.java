package com.lucasurbas.mvc.controller;

import android.os.Handler;

import com.lucasurbas.mvc.model.SimpleItem;
import com.lucasurbas.mvc.model.screenstate.HomeScreenState;
import com.lucasurbas.mvc.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lucas on 3/1/15.
 */
public class HomeController {

    public static void getItemsList(final HomeScreenState screenState) {

        screenState.setRefreshing(true);
        screenState.setStateChanged();

        //Mockup server connection
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                ArrayList<SimpleItem> items = generateItems();
                ArrayList<String> ids = generateIdsList(items);

                screenState.setItemsIdsList(ids);
                screenState.setRefreshing(false);

                for (SimpleItem i : items) {

                    i.addScreenState(screenState);
                    Storage.getInstance().createOrUpdateItem(SimpleItem.class, i);
                }

                screenState.setStateChanged();

            }
        }, 2500);
    }

    public static void addNewItem(final HomeScreenState screenState) {

        screenState.setRefreshing(true);
        screenState.setStateChanged();

        //Mockup server connection
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SimpleItem item = generateItem();

                screenState.addItemId(0, item.getId());
                screenState.setRefreshing(false);

                item.addScreenState(screenState);
                Storage.getInstance().createOrUpdateItem(SimpleItem.class, item);

                screenState.setStateChanged();
            }
        }, 500);
    }

    private static ArrayList<String> generateIdsList(List<SimpleItem> items) {
        ArrayList<String> ids = new ArrayList<>();
        for (SimpleItem i : items) {
            ids.add(i.getId());
        }
        return ids;
    }

    private static ArrayList<SimpleItem> generateItems() {
        ArrayList<SimpleItem> itemsList = new ArrayList<SimpleItem>();
        for (int i = 0; i < 2; i++) {
            itemsList.add(generateItem());
        }
        return itemsList;
    }

    private static SimpleItem generateItem() {
        String id = UUID.randomUUID().toString();
        SimpleItem item = new SimpleItem(id);
        item.setContent("ID:" + id + "\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum bibendum nec metus ac elementum.");

        return item;
    }
}
