package com.kantoniak.greendeer.ui;

import android.view.ContextMenu;

import com.kantoniak.greendeer.proto.Run;

public class RunListItemInfo implements ContextMenu.ContextMenuInfo {

    public Run run;

    public RunListItemInfo(Run run) {
        this.run = run;
    }
}
