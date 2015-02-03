package uz.greenwhite.slidingmenu.support.v9.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.ContentActionBar;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.MenuInflate;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldFragment;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldUtil;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

public abstract class ContentFragment<E, H> extends MoldFragment<E, H> {

    @Override
    protected final ViewSetup onCreateViewSetup(LayoutInflater inflater, ViewGroup container) {
        ViewSetup vsContent = null, vs;

        vs = new ViewSetup(inflater, container, R.layout.mold_refresh_content);

        if (onCreateIndexView() != 0) {
            vsContent = new ViewSetup(inflater, container, onCreateIndexView());
        }

        if (setOnRefreshListener() != null) {
            if (vsContent == null) {
                vsContent = getViewSetupWidthListView();
            }
            ((FrameLayout) vs.id(R.id.liner_layout)).addView(vsContent.view);
            PullToRefreshScrollView pullToRefresh = vs.id(R.id.pull_refresh_scrollview);
            pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

                @Override
                public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                    setOnRefreshListener().setOnRefresh();
                }
            });
        } else {
            vs = new ViewSetup(inflater, container, R.layout.mold_scroll_view);
            FrameLayout fr = vs.id(R.id.mold_scroll_frame);

            if (vsContent != null) {
                fr.addView(vsContent.view);
            } else {
                fr.addView(getViewSetupWidthListView().view);
            }
        }
        return vs;
    }

    protected int onCreateIndexView() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActionBar().setMenus(onActionBarMenu(new MenuInflate()));
        if (setOnHomeClick() != null) {
            getActionBar().setOnHomeClick(setOnHomeClick());
        }
        setFlipper();
    }

    protected MenuInflate onActionBarMenu(MenuInflate menuInflate) {
        return null;
    }

    public ContentActionBar getActionBar() {
        return MoldUtil.getMoldActivity(getActivity()).getContentActionBar();
    }

    protected void setFlipper() {
        getActionBar().setDrawableFlipper(true, (float) 1.0);
    }

    protected Command setOnHomeClick() {
        return null;
    }

    protected PullToRefresh setOnRefreshListener() {
        return null;
    }

    protected void onCompleteRefresh() {
        PullToRefreshScrollView pullToRefresh = getViewSetup().id(R.id.pull_refresh_scrollview);
        if (pullToRefresh != null) {
            pullToRefresh.onRefreshComplete();
        }
    }
}
