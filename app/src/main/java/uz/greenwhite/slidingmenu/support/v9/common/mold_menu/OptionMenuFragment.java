package uz.greenwhite.slidingmenu.support.v9.common.mold_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

public final class OptionMenuFragment extends MoldMenuFragment {

    public static OptionMenuFragment newInstance() {
        return new OptionMenuFragment();
    }

    private ViewSetup vsRoot;

    @Override
    protected ViewSetup onCreateViewSetup(LayoutInflater inflater, ViewGroup container) {
        vsRoot = new ViewSetup(inflater, container, R.layout.mold_option_menu);
        return vsRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
