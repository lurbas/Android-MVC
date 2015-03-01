package com.lucasurbas.mvc.model;

import com.lucasurbas.mvc.model.screenstate.ScreenState;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lucas on 2/27/15.
 */
public abstract class ItemModel extends BaseModel {

    protected Set<ScreenState> screensStates;

    public ItemModel() {
        super();
        screensStates = new HashSet<ScreenState>();
    }

    public void addScreenState(ScreenState state) {
        screensStates.add(state);
    }

    public void removeScreenState(ScreenState state) {
        screensStates.remove(state);
    }

    public Set<ScreenState> getScreensStates() {
        return screensStates;
    }

    public void updateScreensStates(ItemModel item) {
        screensStates.addAll(item.getScreensStates());
    }

    public void notifyDataChanged() {

        Iterator<ScreenState> i = screensStates.iterator();

        while (i.hasNext()) {
            ScreenState screenState = i.next();
            if (screenState == null) {
                i.remove();
                break;
            }
            screenState.setStateChanged();
        }
    }

    public void onRemove(){

        Iterator<ScreenState> i = screensStates.iterator();

        while (i.hasNext()) {
            ScreenState screenState = i.next();
            if (screenState == null) {
                i.remove();
                break;
            }
            screenState.removeItem(getClass(), getId());
        }
    }
}
