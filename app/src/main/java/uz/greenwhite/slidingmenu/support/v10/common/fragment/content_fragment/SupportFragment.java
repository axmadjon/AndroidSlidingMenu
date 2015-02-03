package uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import uz.greenwhite.slidingmenu.support.v10.common.Command;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportDialog;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportMenuContainer;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportSearchQuery;
import uz.greenwhite.slidingmenu.support.v10.common.mold.SupportActivity;
import uz.greenwhite.slidingmenu.support.v10.widget.ViewSetup;

import java.util.ArrayList;
import java.util.List;


public abstract class SupportFragment extends ContentFragment {

    protected abstract ViewSetup onCreateViewSetup(@Nullable LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void onContentCreate(@Nullable Bundle savedInstanceState);

    private final String ARG_FRAGMENT_DATA = "uz.greenwhite.slidingmenu.support.v10.fragment_data";

    private SupportDialog dialog;
    private Parcelable mData;
    public List<SupportMenuContainer> mMenus;
    public SupportSearchQuery searchQuery;
    public int searchQueryIcon;
    private ViewSetup vsRoot;
    private Command hCommand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelable(ARG_FRAGMENT_DATA);
        }
    }

    public <T extends Parcelable> T manageFragmentData(Parcelable data) {
        if (mData == null) {
            mData = data;
        }
        return (T) mData;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mData != null)
            outState.putParcelable(ARG_FRAGMENT_DATA, mData);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vsRoot = onCreateViewSetup(inflater, container);
        return vsRoot.view;
    }

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mMenus = null;
        hCommand = null;
        SupportActivity activity = (SupportActivity) getActivity();
        activity.onContentCreate();
        onContentCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        SupportActivity moldActivity = (SupportActivity) getActivity();
        moldActivity.onContentStarted(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(hCommand != null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (hCommand != null) {
                    hCommand.apply();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected ViewSetup getVsRoot() {
        return vsRoot;
    }

    protected ActionBar getActionBar() {
        SupportActivity activity = (SupportActivity) getActivity();
        return activity.getSupportActionBar();
    }

    protected void showDialog(SupportDialog dialog) {
        this.dialog = dialog;
        dialog.show(getFragmentManager(), "support_dialog");
    }

    protected SupportDialog getDialog() {
        try {
            return dialog;
        } finally {
            dialog = null;
        }
    }

    private List<SupportMenuContainer> getMenus() {
        if (mMenus == null) {
            mMenus = new ArrayList<SupportMenuContainer>();
        }
        return mMenus;
    }

    public void onHomeClick(Command command) {
        this.hCommand = command;
    }

    public void addMenu(int iconId, Command command) {
        getMenus().add(new SupportMenuContainer(iconId, command));
    }

    public void addMenu(View view) {
        getMenus().add(new SupportMenuContainer(view));
    }

    public void setSearchMenu(SupportSearchQuery searchQuery) {
        this.searchQueryIcon = android.R.drawable.ic_menu_search;// R.drawable.gwslib_ic_action_search;
        this.searchQuery = searchQuery;
    }

    public void setSearchMenu(int iconResId, SupportSearchQuery searchQuery) {
        this.searchQueryIcon = iconResId;
        this.searchQuery = searchQuery;
    }

    public void onContentFragmentResult(int requestCode, int resultCode, Intent data) {
    }

    public void onAboveContentPopped(Object result) {
    }

    public void onDialogResult(Object result) {
    }
}
