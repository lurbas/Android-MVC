package com.lucasurbas.mvc.view.layout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.adapter.SimpleItemsAdapter;
import com.lucasurbas.mvc.controller.HomeController;
import com.lucasurbas.mvc.model.screenstate.HomeScreenState;
import com.lucasurbas.mvc.model.screenstate.ScreenState;
import com.lucasurbas.mvc.toolbox.CompareResult;
import com.lucasurbas.mvc.toolbox.IdsComparator;
import com.lucasurbas.mvc.view.interfaces.ILayout;

import java.util.List;

/**
 * Created by Lucas on 2/27/15.
 */
public class HomeLayout extends FrameLayout implements ILayout {

    private static final String TAG = HomeLayout.class.getSimpleName();

    private RecyclerView rvFeed;
    private View emptyView;
    private SwipeRefreshLayout srlRefreshFeed;
    private SimpleItemsAdapter adapter;
    private HomeScreenState screenState;

    public HomeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate();
        prepare();
    }

    private void inflate() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_home_internal, this);
        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
        srlRefreshFeed = (SwipeRefreshLayout) findViewById(R.id.srlRefreshFeed);
        emptyView = findViewById(R.id.emptyView);
    }

    private void prepare() {

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvFeed.setLayoutManager(llm);

        adapter = new SimpleItemsAdapter(getContext());
        rvFeed.setAdapter(adapter);
        srlRefreshFeed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HomeController.addNewItem(screenState);
            }
        });
    }

    @Override
    public void setScreenState(ScreenState screenState) {
        this.screenState = (HomeScreenState) screenState;
    }

    @Override
    public void update() {
        if (screenState == null) {
            return;
        }

        updateRefreshingState(screenState.isRefreshing());
        updateItemsIdsList(screenState.getItemsIdsList());

        resetScreenState(screenState);
    }

    private void resetScreenState(final ScreenState screenState) {
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                HomeLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
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

    private void updateRefreshingState(final boolean refresh) {
        if (srlRefreshFeed.isRefreshing() == refresh) {
            return;
        }
        this.post(new Runnable() {
            @Override
            public void run() {
                srlRefreshFeed.setRefreshing(refresh);
            }
        });
    }
}
