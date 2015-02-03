package uz.greenwhite.slidingmenu.support.v9.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldFragment;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

public abstract class IndexFragment<E, H> extends MoldFragment<E, H> {

    @Override
    protected final ViewSetup onCreateViewSetup(LayoutInflater inflater, ViewGroup container) {
        ViewSetup vsIndexView, vsScrollView;
        vsScrollView = getViewSetupWidthListView();
        if (onCreateIndexView() != 0) {
            vsScrollView = new ViewSetup(inflater, container, onCreateIndexView());
        }
        vsIndexView = new ViewSetup(inflater, container, R.layout.mold_scroll_view);
        FrameLayout fr = vsIndexView.id(R.id.mold_scroll_frame);
        fr.addView(vsScrollView.view);
        return vsIndexView;
    }

    protected int onCreateIndexView() {
        return 0;
    }
}