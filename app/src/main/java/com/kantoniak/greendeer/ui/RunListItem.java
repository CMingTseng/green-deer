package com.kantoniak.greendeer.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.ContextMenu;

import com.kantoniak.greendeer.R;
import com.kantoniak.greendeer.proto.Run;

public class RunListItem extends ConstraintLayout {
    public RunListItem(Context context) {
        super(context);
    }

    public RunListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RunListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        Run run = (Run) getTag(R.id.list_item_tag_id);
        return new RunListItemInfo(run);
    }
}
