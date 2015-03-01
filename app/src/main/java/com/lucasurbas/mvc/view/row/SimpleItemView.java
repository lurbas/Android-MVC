package com.lucasurbas.mvc.view.row;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.controller.SimpleItemController;
import com.lucasurbas.mvc.model.SimpleItem;
import com.lucasurbas.mvc.storage.Storage;
import com.lucasurbas.mvc.view.interfaces.IView;

/**
 * Created by Lucas on 3/1/15.
 */
public class SimpleItemView extends RelativeLayout implements IView {

    private static final String TAG = SimpleItemView.class.getSimpleName();

    private SimpleItem simpleItem;

    private ImageView ivIcon;
    private TextView tvContent;
    private Button bFavorite;
    private Button bDelete;


    public SimpleItemView(Context context) {
        super(context);
        inflate();
        prepare();
    }

    public SimpleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate();
        prepare();
    }

    public SimpleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate();
        prepare();
    }

    private void inflate() {
        LayoutInflater.from(getContext()).inflate(R.layout.row_simple_item_internal, this);
        tvContent = (TextView) findViewById(R.id.tvContent);
        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        bFavorite = (Button) findViewById(R.id.bFavorite);
        bDelete = (Button) findViewById(R.id.bDelete);
    }

    private void prepare() {

        bFavorite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleItem != null) {
                    SimpleItemController.toggleFavorite(simpleItem);
                }
            }
        });

        bDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleItem != null) {
                    SimpleItemController.delete(simpleItem);
                }
            }
        });
    }

    @Override
    public void update(String id) {
        simpleItem = Storage.getInstance().getItemWithId(SimpleItem.class, id);
        if (simpleItem == null) {
            Log.e(TAG, "No item with passed id in Storage");
            return;
        }
        setSimpleItem(simpleItem);
    }

    private void setSimpleItem(SimpleItem item) {

        tvContent.setText(item.getContent());
        if (item.isFavorite()) {
            ivIcon.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            ivIcon.setImageResource(R.drawable.ic_star_outline_black_24dp);
        }
    }
}
