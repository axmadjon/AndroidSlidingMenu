package uz.greenwhite.slidingmenu.support.v9.common.mold_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.FrameLayout;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.Command;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldActivity;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldUtil;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

abstract class MoldMenuFragment extends Fragment {

    public static int SHOW = 1;
    public static int HIDE = 2;

    private ViewSetup vsRoot;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vsRoot = new ViewSetup(inflater, container, R.layout.mold_menu_main);

        ViewSetup vsMenu = onCreateViewSetup(inflater, container);
        if (vsMenu != null) {
            FrameLayout frameLayout = initFrameLayout();
            frameLayout.addView(vsMenu.view);
            ((FrameLayout) vsRoot.id(R.id.content)).addView(frameLayout);
        }
        return vsRoot.view;
    }

    private FrameLayout initFrameLayout() {
        MoldActivity moldActivity = MoldUtil.getMoldActivity(getActivity());
        WindowManager windowManager = moldActivity.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        int height = (defaultDisplay.getHeight() / 2);
        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        ));
        return fl;
    }

    protected ViewSetup onCreateViewSetup(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    protected ViewSetup getViewSetup() {
        return vsRoot;
    }

    public void show() {
        visibility(SHOW);
    }

    public void hide() {
        visibility(HIDE);
    }

    public void visibility(int visible) {
        int vis = View.GONE;
        if (visible == SHOW) {
            vis = View.VISIBLE;
        }
        vsRoot.view.setVisibility(vis);
    }

    protected Command onNegativeButton() {
        return null;
    }

    protected Command onPositiveButton() {
        return null;
    }
}
