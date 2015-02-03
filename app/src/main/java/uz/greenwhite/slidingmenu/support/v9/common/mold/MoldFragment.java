package uz.greenwhite.slidingmenu.support.v9.common.mold;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.collection.MyAdapter;
import uz.greenwhite.slidingmenu.support.v9.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v9.common.ContentFragment;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

public abstract class MoldFragment<E, H> extends Fragment {

    private ViewSetup vsRoot;
    private MoldAdapter adapter;

    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        vsRoot = onCreateViewSetup(inflater, container);
        if (vsRoot == null) {
            vsRoot = getViewSetupWidthListView();
        }
        return vsRoot.view;
    }

    protected ViewSetup onCreateViewSetup(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    protected final ViewSetup getViewSetupWidthListView() {
        return new ViewSetup(getActivity(), R.layout.mold_list_view);
    }

    protected final void replaceContent(ContentFragment contentFragment) {
        MoldUtil.replaceContent(getActivity(), contentFragment);
    }

    protected final void addContent(ContentFragment contentFragment) {
        MoldUtil.addContent(getActivity(), contentFragment);
    }

    protected final void openContent(ContentFragment contentFragment) {
        MoldUtil.openContent(getActivity(), contentFragment);
    }

    //***********************************

    protected final ViewSetup getViewSetup() {
        return vsRoot;
    }

    protected final MoldAdapter getAdapter() {
        return adapter;
    }

    //*****************ListView Adapter

    protected final void setListAdapter(MyArray<E> list) {
        adapter = new MoldAdapter(getActivity());
        adapter.setItems(list);

        for (int i = 0; i < adapter.getCount(); i++) {
            LinearLayout ll = vsRoot.id(R.id.mold_list);
            ViewSetup vsListItem = new ViewSetup(getActivity(), R.layout.mold_list_view_item);
            View adapterView = adapter.getView(i, null, null);
            adapterView.setBackgroundResource(R.drawable.button_selector);
            ((FrameLayout) vsListItem.id(R.id.content_frame)).addView(adapterView);
            ll.addView(vsListItem.view);
        }

        makeContent(0);
    }

    protected void listClick(E item) {
    }

    protected void listLongClick(E item) {
    }

    protected void makeContent(int id) {
    }

    //***************** MoldAdapter

    protected int adapterGetLayoutResource() {
        return 0;
    }

    protected H adapterMakeHolder(View v) {
        return null;
    }

    protected void adapterPopulate(H holder, E item) {
    }

    protected final class MoldAdapter extends MyAdapter<E, H> {

        public MoldAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutResource() {
            return adapterGetLayoutResource();
        }

        @Override
        public H makeHolder(View view) {
            return adapterMakeHolder(view);
        }

        @Override
        public void populate(H holder, E item) {
            adapterPopulate(holder, item);
        }

        @Override
        protected final void onClickListener(E item) {
            listClick(item);
        }

        @Override
        protected final void onLongClickListener(E item) {
            listLongClick(item);
        }
    }
}