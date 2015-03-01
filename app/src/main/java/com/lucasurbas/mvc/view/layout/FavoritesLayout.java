package com.lucasurbas.mvc.view.layout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.adapter.SimpleItemsAdapter;
import com.lucasurbas.mvc.model.screenstate.FavoritesScreenState;
import com.lucasurbas.mvc.model.screenstate.ScreenState;
import com.lucasurbas.mvc.toolbox.CompareResult;
import com.lucasurbas.mvc.toolbox.IdsComparator;
import com.lucasurbas.mvc.view.interfaces.ILayout;

import java.util.List;

/**
 * Created by Lucas on 3/1/15.
 */
public class FavoritesLayout extends FrameLayout implements ILayout {

    private static final String TAG = HomeLayout.class.getSimpleName();

    private RecyclerView rvFeed;
    private View emptyView;
    private SimpleItemsAdapter adapter;
    private FavoritesScreenState screenState;

    public FavoritesLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate();
        prepare();
    }

    private void inflate() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_favorites_internal, this);
        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
        emptyView = findViewById(R.id.emptyView);
    }

    private void prepare() {

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvFeed.setLayoutManager(llm);

        adapter = new SimpleItemsAdapter(getContext());
        rvFeed.setAdapter(adapter);
    }

    @Override
    public void setScreenState(ScreenState screenState) {
        this.screenState = (FavoritesScreenState) screenState;
    }

    @Override
    public void update() {
        if (screenState == null) {
            return;
        }
        updateItemsIdsList(screenState.getItemsIdsList());

        resetScreenState(screenState);
    }

    private void resetScreenState(final ScreenState screenState) {
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                FavoritesLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                screenState.resetState();
                return false;
            }
        });
    }

    private void updateItemsIdsList(List<String> itemsIdsList) {

        if (itemsIdsList.isEmpty()) {
            showEmptyScreen();
        } else {
            hideEmptyScreen();
        }

        final CompareResult compareResult = IdsComparator.compare(adapter.getItemsIds(), itemsIdsList);
        switch (compareResult.getResult()) {

            case SAME_ITEMS:
                adapter.notifyItemRangeChanged(0, itemsIdsList.size());
                break;

            case ONE_ITEM_ADDED:
                adapter.setItemsIds(itemsIdsList);
                adapter.notifyItemInserted(compareResult.getChangedPosition());
                break;

            case ONE_ITEM_REMOVED:
                adapter.setItemsIds(itemsIdsList);
                adapter.notifyItemRemoved(compareResult.getChangedPosition());
                break;

            default:
            case MANY_ITEMS_CHANGED:
                adapter.setItemsIds(itemsIdsList);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void hideEmptyScreen() {
        emptyView.setVisibility(View.GONE);
    }

    private void showEmptyScreen() {
        emptyView.setVisibility(View.VISIBLE);
    }

}
