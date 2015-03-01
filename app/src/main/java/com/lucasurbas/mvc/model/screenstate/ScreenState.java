package com.lucasurbas.mvc.model.screenstate;

import com.lucasurbas.mvc.model.BaseModel;
import com.lucasurbas.mvc.model.interfaces.IDataChanged;
import com.lucasurbas.mvc.model.ItemModel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Lucas on 2/27/15.
 */
public abstract class ScreenState extends BaseModel {

    protected Set<IDataChanged> listeners = new HashSet<IDataChanged>();
    protected boolean stateChanged;

    public void addOnDataChangedListener(IDataChanged listener) {
        synchronized (listeners) {
            if (listener == null) {
                throw new IllegalArgumentException("listener is null");
            }
            listeners.add(listener);
            listener.onDataChanged(getId());
        }
    }

    public void removeOnDataChangedListener(IDataChanged listener) {
        synchronized (listeners) {
            if (listener == null) {
                throw new IllegalArgumentException("listener is null");
            }
            listeners.remove(listener);
        }
    }

//    public void checkIfChanged() {
//        if (stateChanged) {
//            for (IDataChanged l : listeners) {
//                l.onDataChanged(getId());
//            }
//        }
//    }

    public void setStateChanged() {
        synchronized (listeners) {
            if (!stateChanged) {
                stateChanged = true;
                for (IDataChanged l : listeners) {
                    l.onDataChanged(getId());
                }
            }
        }
    }

    public void resetState() {
        stateChanged = false;
    }

    protected void removeItem(List<String> list, String id) {
        Iterator<String> i = list.iterator();
        while (i.hasNext()) {
            if (id.equals(i.next())) {
                i.remove();
            }
        }
        setStateChanged();
    }

    public abstract <T extends ItemModel> void removeItem(Class<T> clazz, String id);
}
