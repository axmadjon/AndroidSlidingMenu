package uz.greenwhite.slidingmenu.support.v9.common.mold;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.IndexFragment;
import uz.greenwhite.slidingmenu.support.v9.common.mold_menu.OptionMenuFragment;

abstract class BaseActivity extends SlidingFragmentActivity {

    protected SlidingMenu sm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.mold_index);

        sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }

    protected void openIndexContent(Fragment f) {
        setFragment(R.id.mold_menu_frame, f);
        setSlidingActionBarEnabled(true);
        sm.showMenu();
    }

    private void setFragment(int resId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resId, fragment)
                .commit();
    }

    protected void setIndexGone() {
        Display d = getWindowManager().getDefaultDisplay();
        sm.setBehindOffsetInt(d.getWidth());
    }

    protected void setIndexVisible() {
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    }

    protected IndexFragment getIndexContent() {
        return (IndexFragment) getSupportFragmentManager().findFragmentById(R.id.mold_menu_frame);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }

    protected void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    protected void onPrepareMenu(int resId) {
        setFragment(resId, OptionMenuFragment.newInstance());
    }

    protected void debugLog(String msg) {
        Log.d("LIB", msg);
    }
}