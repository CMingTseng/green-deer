package com.kantoniak.greendeer.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ItemTouchListener implements RecyclerView.OnItemTouchListener {

    private final RecyclerView recyclerView;
    private final RecyclerItemClickListener clickListener;
    private final GestureDetector gestureDetector;

    public ItemTouchListener(Context context, final RecyclerView recyclerView, final RecyclerItemClickListener clickListener) {
        this.recyclerView = recyclerView;
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child == null || clickListener == null) {
                    return;
                }
                clickListener.onLongPress(child, recyclerView.getChildAdapterPosition(child));
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, recyclerView.getChildAdapterPosition(child));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        // Do nothing
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // Do nothing
    }
}