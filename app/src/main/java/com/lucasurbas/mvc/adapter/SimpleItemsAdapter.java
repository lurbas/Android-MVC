package com.lucasurbas.mvc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.view.interfaces.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 3/1/15.
 */
public class SimpleItemsAdapter extends
        RecyclerView.Adapter<SimpleItemsAdapter.ViewHolder> {

    private Context context;
    private List<String> itemsIds;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    public SimpleItemsAdapter(Context context) {
        this.context = context;
        this.itemsIds = new ArrayList<String>();
    }

    @Override
    public SimpleItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_simple_item, parent, false);
        return new SimpleItemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleItemsAdapter.ViewHolder viewHolder, int position) {

        final View itemView = viewHolder.itemView;
        String itemId = itemsIds.get(position);
        ((IView) itemView).update(itemId);
    }

    @Override
    public int getItemCount() {
        return itemsIds.size();
    }

    public void setItemsIds(List<String> itemsIds) {
        this.itemsIds = new ArrayList<String>(itemsIds);
    }

    public List<String> getItemsIds() {
        return itemsIds;
    }

}
