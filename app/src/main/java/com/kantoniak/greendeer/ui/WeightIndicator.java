package com.kantoniak.greendeer.ui;

import android.content.Context;
import android.util.AttributeSet;

public class WeightIndicator extends CompletnessIndicator {
    public WeightIndicator(Context context) {
        super(context);
    }

    public WeightIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeightIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void updatePercentage() {
        double diff = Math.round((value-goal) * 10.d) / 10.d;
        this.mTextPercentage.setText((value > goal ? "+" : "") + valuePrefix + diff + valueSuffix);
    }
}
