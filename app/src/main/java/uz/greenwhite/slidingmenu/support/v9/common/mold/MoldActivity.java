package uz.greenwhite.slidingmenu.support.v9.common.mold;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.ContentFragment;
import uz.greenwhite.slidingmenu.support.v9.common.IndexFragment;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.ContentActionBar;
import uz.greenwhite.slidingmenu.support.v9.common.mold_menu.OptionMenuFragment;

public final class MoldActivity extends BaseActivity {

    private static final String ARG_CLASS = "mac";
    private static final String ARG_BUNDLE = "mab";

    private static void open(Context ctx, Class<? extends Fragment> cls, Bundle args) {
        Intent i = new Intent(ctx, MoldActivity.class);
        i.putExtra(ARG_CLASS, cls);
        if (args != null) {
            i.putExtra(ARG_BUNDLE, args);
        }
        ctx.startActivity(i);
    }

    public static void openIndex(Context ctx, Class<? extends MoldFragment> cls, Bundle args) {
        open(ctx, cls, args);
    }

    public static void openContent(Context ctx, Class<? extends ContentFragment> cls, Bundle args) {
        open(ctx, cls, args);
    }

    private boolean isContent;
    private boolean isMenuShow = false;
    private ContentActionBar contentActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mold_content);
        contentActionBar = ContentActionBar.newInstance();
        setActionBar(contentActionBar);
        onPrepareMenu(R.id.mold_content_menu);
        Drawable d;

        Fragment fragment = createFragment();
        if (fragment instanceof IndexFragment) {
            isContent = false;
            openIndexContent(fragment);
        } else if (fragment instanceof ContentFragment) {
            isContent = true;
            replaceContent((ContentFragment) fragment);
        }
    }

    private Fragment createFragment() {
        try {
            Bundle extras = getIntent().getExtras();
            Bundle args = extras.getBundle(ARG_BUNDLE);

            Class<?> cls = (Class<?>) extras.getSerializable(ARG_CLASS);
            Fragment fragment = (Fragment) cls.newInstance();
            if (args != null) {
                fragment.setArguments(args);
            }

            if (fragment instanceof IndexFragment) {
                return (IndexFragment) fragment;
            } else {
                return (ContentFragment) fragment;
            }

        } catch (Exception e) {
            e.printStackTrace();//TODO
        }
        return null;
    }

    private void openContent(ContentFragment cf, boolean addToBackStack) {
        hideSoftKeyboard();
        if (!addToBackStack) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mold_content_frame, cf);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();

        if (isContent) {
            setContent(isContent);
        }
        showContents();

        getOptionMenuFragment().hide();
        isMenuShow = false;
    }

    public void setActionBar(ContentActionBar actionBar) {
        if (actionBar == null) {
            throw new NullPointerException("ActionBar is null");
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mold_content_action_bar, actionBar)
                .commit();
    }

    public FrameLayout getActionBarLayout() {
        return (FrameLayout) findViewById(R.id.mold_content_action_bar);
    }

    public void showIndexContents() {
        sm.showMenu();
    }

    public void showContents() {
        sm.showContent();
    }

    @Override
    public IndexFragment getIndexContent() {
        return super.getIndexContent();
    }

    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    public ContentActionBar getContentActionBar() {
        return (ContentActionBar) getSupportFragmentManager().findFragmentById(R.id.mold_content_action_bar);
    }

    public OptionMenuFragment getOptionMenuFragment() {
        return (OptionMenuFragment) getSupportFragmentManager().findFragmentById(R.id.mold_content_menu);
    }

    public void replaceContent(ContentFragment content) {
        openContent(content, false);
    }

    public void addContent(ContentFragment content) {
        openContent(content, true);
    }

    public void openContent(ContentFragment content) {
        setContent(true);
        addContent(content);
    }

    public void setContent(boolean isContent) {
        if (isContent) {
            setIndexGone();
        } else {
            setIndexVisible();
        }
    }

    @Override
    public boolean onKeyUp(int key, KeyEvent event) {
        if (KeyEvent.KEYCODE_MENU == key) {
            int visible = OptionMenuFragment.SHOW;
            if (isMenuShow) {
                visible = OptionMenuFragment.HIDE;
                isMenuShow = false;
            } else {
                isMenuShow = true;
            }
            getOptionMenuFragment().visibility(visible);
            return true;
        }
        return super.onKeyUp(key, event);
    }

    public void setOnSlidingListener(SlidingMenu.OnSlidingListener slidingListener) {
        sm.setOnSlidingListener(slidingListener);
    }
}
