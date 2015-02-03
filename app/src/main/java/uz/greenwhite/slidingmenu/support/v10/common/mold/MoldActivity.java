package uz.greenwhite.slidingmenu.support.v10.common.mold;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v10.SupportApplication;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportMenuContainer;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportSearchQuery;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.error.SystemError;
import uz.greenwhite.slidingmenu.support.v10.service.TaskGroup;
import uz.greenwhite.slidingmenu.support.v10.service.TaskManager;
import uz.greenwhite.slidingmenu.support.v10.service.TaskService;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.ActivityLife;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.FragmentLife;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.LifeCycle;
import uz.greenwhite.slidingmenu.support.v10.service.request.TaskRequest;

import java.util.List;

class MoldActivity extends MyActivity {

    protected int mMenuIdSeq = 10;
    protected MyArray<SupportMenuContainer> mMenuItems;
    protected Object contentResult;
    protected Parcelable mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityContentView());

        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelable(ARG_DATA);
        } else {
            Fragment fragment = createFragment();
            if (fragment != null) {
                replaceContent((SupportFragment) fragment);
            } else {
                throw new NullPointerException("fragment is null");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mData != null) {
            outState.putParcelable(ARG_DATA, mData);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityLife.instance.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityLife.instance.onStop();
    }

    protected <T> T getActivityExtras(String arg_name) {
        Bundle extras = getIntent().getExtras();
        return (T) extras.get(arg_name);
    }

    protected int getActivityContentView() {
        int resId = R.layout.support_mold_content;
        Boolean fullDisplay = getActivityExtras(ARG_FULL);
        if (fullDisplay) {
            resId = R.layout.support_full_content;
        }
        return resId;
    }

    protected Fragment createFragment() {
        try {
            Class<?> cls = getActivityExtras(ARG_CLASS);
            Bundle args = getActivityExtras(ARG_BUNDLE);
            Fragment fragment = (Fragment) cls.newInstance();
            if (args != null) {
                fragment.setArguments(args);
            }
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parcelable getData() {
        return mData;
    }

    public void setData(Parcelable data) {
        this.mData = data;
    }

    public void popContent(Object result) {
        getSupportFragmentManager().popBackStack();
        this.contentResult = result;
    }


    public SupportFragment getContentFragment() {
        return (SupportFragment) getSupportFragmentManager().findFragmentById(R.id.support_content);
    }

    public void replaceContent(SupportFragment content) {
        openContent(content, false);
    }

    public void addContent(SupportFragment content) {
        openContent(content, true);
    }

    private void openContent(SupportFragment cf, boolean addToBackStack) {
        hideSoftKeyboard();
        if (!addToBackStack) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.support_content, cf);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    protected void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() ||
                    y < w.getTop() || y > w.getBottom())) {
                hideSoftKeyboard();
            }
        }
        return ret;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        this.mMenuItems = MyArray.emptyArray();

        MyArray<SupportMenuContainer> menuItems = MyArray.emptyArray();

        SupportFragment contentFragment = getContentFragment();
        if (contentFragment != null) {
            MyArray<SupportMenuContainer> contentMenus = MyArray.emptyArray();
            if (contentFragment.mMenus != null) {
                contentMenus = MyArray.from(contentFragment.mMenus);
            }
            menuItems = contentMenus;
            if (contentFragment.searchQuery != null) {
                addSearchMenu(menu, contentFragment.searchQuery, contentFragment.searchQueryIcon);
            }
        }

        for (SupportMenuContainer m : menuItems) {
            if (m.id == 0) {
                m.id = mMenuIdSeq++;
            }
            MenuItem menuItem = menu.add(Menu.NONE, m.id, Menu.NONE, "");
            if (m.view != null) {
                MenuItemCompat.setActionView(menuItem, m.view);
            } else {
                menuItem.setIcon(m.iconResId);
            }
            MenuItemCompat.setShowAsAction(menuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        }

        this.mMenuItems = menuItems;

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        for (SupportMenuContainer m : mMenuItems) {
            if (m.id == id) {
                if (m.command != null) {
                    m.command.apply();
                }
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SupportFragment contentFragment = getContentFragment();
        contentFragment.onContentFragmentResult(requestCode, resultCode, data);
    }

    public void onContentCreate() {
    }

    public void onContentStarted(SupportFragment that) {
        SupportFragment contentFragment = getContentFragment();
        if (contentFragment == that) {
            try {
                if (contentResult != null) {
                    contentFragment.onAboveContentPopped(contentResult);
                }
            } finally {
                contentResult = null;
            }
        }
    }

    public void onContentStoped() {
    }

    protected void addSearchMenu(Menu menu, SupportSearchQuery searchQuery, int searchQueryIcon) {
        MenuItem item = menu.add(Menu.NONE, 1, Menu.NONE, "");
        item.setIcon(searchQueryIcon);
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW |
                MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        SearchView searchView = new SearchView(this);
        searchView.setIconified(false);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(searchQuery);
        searchView.clearFocus();
    }


    public synchronized void taskRequests(TaskManager taskManager) {
        try {
            List<TaskRequest> t = taskManager.taskGroup.taskRequests;
            MyTaskService taskService = new MyTaskService(taskManager.taskGroup, t);
            taskService.execute(t);
        } catch (Exception ex) {
            throw new SystemError(ex);
        }
    }

    class MyTaskService extends TaskService {

        final TaskGroup taskGroup;

        MyTaskService(TaskGroup taskGroup, List<TaskRequest> taskRequest) {
            super(taskRequest);
            this.taskGroup = taskGroup;
        }

        @Override
        public void onStart() {
            taskGroup.onStart();
        }

        @Override
        public void onStop() {
            taskGroup.onStop();
        }

        @Override
        public void onResult(long id, String result) {
            TaskRequest task = findTask(id);
            SupportFragment contentFragment = getContentFragment();
            SupportApplication.requestUtil.saveRequest(contentFragment.getClass().getName(), result);
            if (FragmentLife.instance.getState() != LifeCycle.STOP) {
                task.parsResult(result);
            }
        }
    }
}
