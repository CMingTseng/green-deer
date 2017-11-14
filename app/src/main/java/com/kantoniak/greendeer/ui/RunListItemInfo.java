package com.kantoniak.greendeer.ui;

import android.view.ContextMenu;

public class RunListItemInfo implements ContextMenu.ContextMenuInfo {

    public int id;

    public RunListItemInfo(int id) {
        this.id = id;
    }
}
