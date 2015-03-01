package com.lucasurbas.mvc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.model.interfaces.IDataChanged;
import com.lucasurbas.mvc.model.screenstate.FavoritesScreenState;
import com.lucasurbas.mvc.storage.Storage;
import com.lucasurbas.mvc.view.interfaces.ILayout;

/**
 * Created by Lucas on 3/1/15.
 */
public class FavoritesFragment extends BaseFragment {

    private FavoritesScreenState screenState;
    private ILayout layout;
    private IDataChanged stateListener = new IDataChanged() {
        @Override
        public void onDataChanged(String id) {
            layout.update();
        }
    };

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenState = (FavoritesScreenState) Storage.getInstance().getOrCreateScreenState(new FavoritesScreenState());
        //FavoritesController.getItemsList(screenState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_favorites, container, false);
        layout = (ILayout) root;
        return root;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        layout.setScreenState(screenState);
        screenState.addOnDataChangedListener(stateListener);
    }

    @Override
    public void onDestroyView() {

        screenState.removeOnDataChangedListener(stateListener);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        //Storage.getInstance().deleteScreenStateWithId(screenState.getId());
        super.onDestroy();
    }
}
