package uz.greenwhite.slidingmenu.support.v9.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MyViewGroup extends View {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return executeKeyEvent(event);
    }

    boolean executeKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    debugLog("KEYCODE_DPAD_LEFT");
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    debugLog("KEYCODE_DPAD_RIGHT");
                    break;
            }
        }
        return true;
    }

    public static void debugLog(String msg) {
        Log.d("LIB", msg);
    }
}
