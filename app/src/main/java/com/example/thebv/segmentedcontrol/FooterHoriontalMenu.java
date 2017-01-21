package com.example.thebv.segmentedcontrol;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by thebv on 02/12/2016.
 */

public class FooterHoriontalMenu extends LinearLayout {
    public FooterHoriontalMenu(Context context) {
        super(context);
    }

    public FooterHoriontalMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterHoriontalMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FooterHoriontalMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
