package uz.greenwhite.slidingmenu.support.v10.common.fragment.refreash_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ScrollView;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.widget.ViewSetup;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshBase;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshScrollView;

public abstract class SupportRefreshFragment extends SupportFragment {

    protected abstract ViewSetup onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void onRefreshing(PullToRefreshBase<ScrollView> refreshView);


    private PullToRefreshScrollView refreshScrollView;

    @Override
    protected ViewSetup onCreateViewSetup(@Nullable LayoutInflater inflater, @Nullable ViewGroup container) {
        ViewSetup vsRoot = new ViewSetup(inflater, container, R.layout.support_refresh_content);
        refreshScrollView = vsRoot.id(R.id.pull_to_refresh);
        refreshScrollView.addView(onCreateView(inflater, container).view);
        return vsRoot;
    }

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        refreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                onRefreshing(refreshView);
            }
        });
    }

    protected void completeRefresh() {
        refreshScrollView.onRefreshComplete();
    }
}
