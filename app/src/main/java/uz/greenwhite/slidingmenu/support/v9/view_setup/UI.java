package uz.greenwhite.slidingmenu.support.v9.view_setup;

import android.view.View;


public class UI {

    public static <T extends View> T id(View parent, int resId) {
        View v = parent.findViewById(resId);
        if (v == null) {
            throw new NullPointerException("View is null");
        }
        return (T) v;
    }
}
