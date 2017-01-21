package com.example.thebv.segmentedcontrol;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thebv on 02/12/2016.
 */


//    Base Using
//
//    ArrayList<String> arrItem = new ArrayList<>();
//    arrItem.add("Tab 1");
//    arrItem.add("Tab 2");
//    arrItem.add("Tab 3");
//    arrItem.add("Tab 4");
//
//    int strokeWidth = (int) getResources().getDimension(R.dimen._1sdp);
//    int cornerRadius = (int) getResources().getDimension(R.dimen._5sdp);
//    int colorStroke = getResources().getColor(R.color.colorAccent);
//    int colorSelected = getResources().getColor(R.color.colorAccent);
//    int colorNormal = getResources().getColor(R.color.white);
//    int textSize = (int) getResources().getDimension(R.dimen._11ssp);
//    int colorTextSelected = getResources().getColor(R.color.white);
//    int colorTextNormal = getResources().getColor(R.color.colorAccent);
//    int positionSelected = 0;
//
//    segmentedControl.setup(arrItem, strokeWidth,
//            cornerRadius,
//            colorStroke,
//            colorSelected,
//            colorNormal,
//            textSize,
//            colorTextSelected,
//            colorTextNormal,
//            positionSelected,
//            new SegmentedHoriontalControl.OnItemSegmentedClickListener() {
//                @Override
//                public void onItemClick(View view, int position, String text) {
//                    Log.d("thebv", "position = " + position + ", text = " + text);
//                }
//                });

public class SegmentedHoriontalControl extends LinearLayout {

    private Context context = null;
    private ArrayList<String> arrItem = new ArrayList<>();
    private OnItemSegmentedClickListener itemClickListener = null;

    private GradientDrawable gradientDrawableGroup = null;

    private GradientDrawable gradientDrawableFistNormal = null;
    private GradientDrawable gradientDrawableCenterNormal = null;
    private GradientDrawable gradientDrawableLastNormal = null;

    private GradientDrawable gradientDrawableFistSelected = null;
    private GradientDrawable gradientDrawableCenterSelected = null;
    private GradientDrawable gradientDrawableLastSelected = null;
    private int strokeWidth;
    private int cornerRadius;
    private int colorStroke;
    private int colorSelected;
    private int colorNormal;
    private int positionSelected;
    private int textSize;
    private int colorTextSelected;
    private int colorTextNormal;

    public SegmentedHoriontalControl(Context context) {
        super(context);
        this.context = context;
    }

    public SegmentedHoriontalControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SegmentedHoriontalControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SegmentedHoriontalControl(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public void setup(ArrayList<String> arrItem,
                      int strokeWidth,
                      int cornerRadius,
                      int colorStroke,
                      int colorSelected,
                      int colorNormal,
                      int textSize,
                      int colorTextSelected,
                      int colorTextNormal,
                      int positionSelected,
                      OnItemSegmentedClickListener itemClickListener) {
        this.arrItem = arrItem;
        this.itemClickListener = itemClickListener;
        this.strokeWidth = strokeWidth;
        this.cornerRadius = cornerRadius;
        this.colorStroke = colorStroke;
        this.colorSelected = colorSelected;
        this.colorNormal = colorNormal;
        this.textSize = textSize;
        this.colorTextSelected = colorTextSelected;
        this.colorTextNormal = colorTextNormal;
        this.positionSelected = positionSelected;
        setupBackground();
        createView();
    }

    private void setupBackground() {
        gradientDrawableGroup = new GradientDrawable();
        gradientDrawableGroup.setColor(colorStroke);
        gradientDrawableGroup.setCornerRadius(cornerRadius);
        gradientDrawableGroup.setStroke(strokeWidth, colorStroke);

        gradientDrawableFistNormal = new GradientDrawable();
        gradientDrawableFistNormal.setColor(colorNormal);
        gradientDrawableFistNormal.setCornerRadii(new float[]{cornerRadius, cornerRadius, 0, 0, 0, 0, cornerRadius, cornerRadius});

        gradientDrawableFistSelected = new GradientDrawable();
        gradientDrawableFistSelected.setColor(colorSelected);
        gradientDrawableFistSelected.setCornerRadii(new float[]{cornerRadius, cornerRadius, 0, 0, 0, 0, cornerRadius, cornerRadius});

        gradientDrawableCenterNormal = new GradientDrawable();
        gradientDrawableCenterNormal.setColor(colorNormal);
        gradientDrawableCenterNormal.setCornerRadius(0);

        gradientDrawableCenterSelected = new GradientDrawable();
        gradientDrawableCenterSelected.setColor(colorSelected);
        gradientDrawableCenterSelected.setCornerRadius(0);

        gradientDrawableLastNormal = new GradientDrawable();
        gradientDrawableLastNormal.setColor(colorNormal);
        gradientDrawableLastNormal.setCornerRadii(new float[]{0, 0, cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0});

        gradientDrawableLastSelected = new GradientDrawable();
        gradientDrawableLastSelected.setColor(colorSelected);
        gradientDrawableLastSelected.setCornerRadii(new float[]{0, 0, cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0});
    }

    private void createView() {
        removeAllViews();

        setBackgroundDrawable(gradientDrawableGroup);
        setPadding(strokeWidth, strokeWidth, strokeWidth, strokeWidth);

        if (arrItem == null || context == null)
            return;

        for (final String obj : arrItem) {

            TextView textView = new TextView(context);
            textView.setText(obj);


            textView.setTextColor(colorTextNormal);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            textView.setLayoutParams(param);
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view, arrItem.indexOf(obj), obj);
                    positionSelected = arrItem.indexOf(obj);
                    createView();
                }
            });
            textView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    return false;
                }
            });

            if (arrItem.size() == 0) {
                if (arrItem.indexOf(obj) == positionSelected) {
                    textView.setTextColor(colorTextSelected);
                    textView.setBackgroundDrawable(gradientDrawableLastSelected);
                } else {
                    textView.setTextColor(colorTextNormal);
                    textView.setBackgroundDrawable(gradientDrawableLastNormal);
                }
            } else {
                if (arrItem.indexOf(obj) == positionSelected) {
                    textView.setTextColor(colorTextSelected);
                    if (arrItem.indexOf(obj) == 0)
                        textView.setBackgroundDrawable(gradientDrawableFistSelected);
                    else if (arrItem.indexOf(obj) == arrItem.size() - 1)
                        textView.setBackgroundDrawable(gradientDrawableLastSelected);
                    else
                        textView.setBackgroundDrawable(gradientDrawableCenterSelected);
                } else {
                    textView.setTextColor(colorTextNormal);
                    if (arrItem.indexOf(obj) == 0)
                        textView.setBackgroundDrawable(gradientDrawableFistNormal);
                    else if (arrItem.indexOf(obj) == arrItem.size() - 1)
                        textView.setBackgroundDrawable(gradientDrawableLastNormal);
                    else
                        textView.setBackgroundDrawable(gradientDrawableCenterNormal);
                }
            }

            addView(textView);

            if (arrItem.size() != 0 && arrItem.indexOf(obj) != arrItem.size() - 1) {
                View spaceItem = new View(context);
                spaceItem.setBackgroundColor(colorStroke);

                LinearLayout.LayoutParams paramSpace = new LinearLayout.LayoutParams(
                        strokeWidth,
                        LayoutParams.MATCH_PARENT);

                spaceItem.setLayoutParams(paramSpace);
                addView(spaceItem);
            }
        }

        requestLayout();
    }


    public interface OnItemSegmentedClickListener {
        void onItemClick(View view, int position, String text);
    }
}
