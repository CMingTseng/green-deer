package com.kantoniak.greendeer.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.kantoniak.greendeer.R;

public class CompletnessIndicator extends ConstraintLayout {

    private TextView mTextCategory;
    protected TextView mTextPercentage;
    private TextView mTextNumbers;

    protected String valuePrefix = "";
    protected String valueSuffix = "";
    protected double goal = 1.d;
    protected double value = 1.d;

    public CompletnessIndicator(Context context) {
        super(context);
        setupLayout(context);
    }

    public CompletnessIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupLayout(context);
    }

    public CompletnessIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupLayout(context);
    }

    private void setupLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_completness_indicator, this, true);

        mTextCategory = (TextView) findViewById(R.id.text_category);
        mTextPercentage = (TextView) findViewById(R.id.text_percentage);
        mTextNumbers = (TextView) findViewById(R.id.text_numbers);

        updatePercentage();
    }

    protected void updatePercentage() {
        mTextPercentage.setText(Math.round(value * 1000.d/goal) / 10.d + "%");
    }

    private void updateBottomLine() {
        String text = valuePrefix+(Math.round(value * 10.d) / 10.d)+valueSuffix +" / "+
                      valuePrefix+(Math.round(goal * 10.d) / 10.d)+valueSuffix;
        mTextNumbers.setText(text);
    }

    public void setCategory(String category) {
        this.mTextCategory.setText(category);
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
        updateBottomLine();
    }

    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
        updateBottomLine();
    }

    public void setGoal(double goal) {
        this.goal = goal;
        updatePercentage();
        updateBottomLine();
    }

    public void setValue(double value) {
        this.value = value;
        updatePercentage();
        updateBottomLine();
    }
}
