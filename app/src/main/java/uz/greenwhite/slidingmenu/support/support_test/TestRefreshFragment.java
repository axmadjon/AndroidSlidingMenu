package uz.greenwhite.slidingmenu.support.support_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.refreash_fragment.SupportRefreshFragment;
import uz.greenwhite.slidingmenu.support.v10.widget.ViewSetup;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshBase;

public class TestRefreshFragment extends SupportRefreshFragment {

    private ViewSetup vsRoot;

    @Override
    protected ViewSetup onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container) {
        vsRoot = new ViewSetup(inflater, container, R.layout.test_refresh);
        return vsRoot;
    }

    @Override
    protected void onRefreshing(PullToRefreshBase<ScrollView> refreshView) {
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        super.onContentCreate(savedInstanceState);
        vsRoot.textView(R.id.test_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeRefresh();
                addContent(SupportFragment.newInstance(TestRefreshListFragment.class));
            }
        });
    }
}
