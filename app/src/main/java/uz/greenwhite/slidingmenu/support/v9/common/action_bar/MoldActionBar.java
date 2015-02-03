package uz.greenwhite.slidingmenu.support.v9.common.action_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

abstract class MoldActionBar extends Fragment {

    private ViewSetup vsActionBar;

    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        vsActionBar = onCreateActionBarView(inflater, container);

        if (vsActionBar == null) {
            vsActionBar = new ViewSetup(inflater, container, R.layout.mold_action_bar);//TODO
        }
        return vsActionBar.view;
    }

    protected ViewSetup onCreateActionBarView(LayoutInflater inflater,
                                              @Nullable ViewGroup container) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
