package com.kantoniak.greendeer.ui;

import android.view.View;

public interface RecyclerItemClickListener {
    void onClick(View view, int position);
    void onLongPress(View view,int position);
}
