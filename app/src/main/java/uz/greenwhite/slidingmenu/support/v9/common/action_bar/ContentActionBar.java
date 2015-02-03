package uz.greenwhite.slidingmenu.support.v9.common.action_bar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.Command;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldActivity;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldUtil;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;
import uz.greenwhite.slidingmenu.support.v9.widget.DrawerArrowDrawable;

public final class ContentActionBar extends MoldActionBar {

    public static ContentActionBar newInstance() {
        return new ContentActionBar();
    }

    public ViewSetup vsRoot, vsLeft, vsRight;
    private DrawerArrowDrawable drawerArrowDrawable;
    private boolean flip = true;

    @Override
    protected ViewSetup onCreateActionBarView(LayoutInflater inflater,
                                              @Nullable ViewGroup container) {
        vsRoot = new ViewSetup(inflater, container, R.layout.mold_content_action_bar);
        return vsRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLeftActionBar();
        initRightActionBar();
        setBackgroundColor(0xffe2e2e2);

        Resources resources = getResources();

        drawerArrowDrawable = new DrawerArrowDrawable(resources);
        drawerArrowDrawable.setStrokeColor(resources.getColor(R.color.dark_gray));

        vsRoot.imageView(R.id.drawable).setImageDrawable(drawerArrowDrawable);
        ((MoldActivity) getActivity()).setOnSlidingListener(new SlidingMenu.OnSlidingListener() {
            @Override
            public void onListening(int position, float positionOffset, int positionOffsetPixels) {
                if (flip) {
                    boolean flipped;
                    String s = Float.toString(positionOffset);
                    float flo = Float.parseFloat(s.substring(1));

                    if (flo == 0.84) {
                        flipped = true;
                        drawerArrowDrawable.setFlip(flipped);
                    } else if (flo == 0.11) {
                        flipped = false;
                        drawerArrowDrawable.setFlip(flipped);
                    }
                    drawerArrowDrawable.setParameter(flo);
                }
            }
        });
    }

    public void setDrawableFlipper(boolean flipped, float offset) {
        this.flip = !flipped;
        drawerArrowDrawable.setFlip(flipped);
        drawerArrowDrawable.setParameter(offset);
    }

    private void initLeftActionBar() {
        vsLeft = new ViewSetup(getActivity(), R.layout.mold_content_left_action_bar);
        ((FrameLayout) vsRoot.id(R.id.fl_left)).addView(vsLeft.view);
    }

    private void initRightActionBar() {
        vsRight = new ViewSetup(getActivity(), R.layout.mold_content_right_action_bar);
    }

    private void setMenuToRight(ViewSetup v, final Command command) {
        FrameLayout fl = new ViewSetup(getActivity(),
                R.layout.mold_action_bar_right_row).id(R.id.mold_action_bar_right_row);
        fl.setBackgroundResource(R.drawable.button_selector);
        fl.addView(v.view);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.apply();
            }
        });
        LinearLayout ll = vsRight.id(R.id.mold_content_right);
        ll.addView(fl);
    }

    public final ContentActionBar setTitleIcon(Bitmap icon) {
        vsLeft.imageView(R.id.icon).setImageBitmap(icon);
        return this;
    }

    public final ContentActionBar setTitleIcon(int resId) {
        vsLeft.imageView(R.id.icon).setImageDrawable(getActivity().getResources().getDrawable(resId));
        return this;
    }

    public final ContentActionBar setLogo(Bitmap logo) {
        vsRoot.imageView(R.id.drawable).setVisibility(View.GONE);
        vsLeft.imageView(R.id.logo).setVisibility(View.VISIBLE);
        vsLeft.imageView(R.id.logo).setImageBitmap(logo);
        return this;
    }

    public final ContentActionBar setLogo(int resId) {
        vsRoot.imageView(R.id.drawable).setVisibility(View.GONE);
        vsLeft.imageView(R.id.logo).setVisibility(View.VISIBLE);
        vsLeft.imageView(R.id.logo).setImageDrawable(getActivity().getResources().getDrawable(resId));
        return this;
    }

    public void showDrawable() {
        vsRoot.imageView(R.id.drawable).setVisibility(View.VISIBLE);
        vsLeft.imageView(R.id.logo).setVisibility(View.GONE);
    }

    public final ContentActionBar setTitle(CharSequence title) {
        //Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.show_title_action_bar);
        vsLeft.textView(R.id.title).setText(title);
        // vsLeft.textView(R.id.title).startAnimation(anim);
        return this;
    }

    public final ContentActionBar setTitle(int resId) {
        return setTitle(getString(resId));
    }

    public final ContentActionBar setMenus(MenuInflate inflate) {

        FrameLayout frRight = vsRoot.id(R.id.fl_right);
        ((LinearLayout) vsRight.id(R.id.mold_content_right)).removeAllViews();
        frRight.removeAllViews();

        if (inflate == null) {
            return this;
        }
        if (inflate.size() == 0) {
            return this;
        }

        for (MenuItem menuItem : inflate.menuItems) {
            ViewSetup vs = new ViewSetup(getActivity(), R.layout.mold_action_bar_menu_item);
            int iconResId = menuItem.iconRes;
            if (iconResId != 0) {
                Drawable icon = getActivity().getResources().getDrawable(iconResId);
                ((ImageView) vs.id(R.id.icon)).setImageDrawable(icon);
            } else {
                vs = menuItem.vs;
            }
            setMenuToRight(vs, menuItem.command);
        }
        (frRight).addView(vsRight.view);
        return this;
    }

    public final ContentActionBar setOnHomeClick(final Command command) {
        return setOnHomeClick(command, 0);
    }

    public final ContentActionBar setOnHomeClick(final Command command, int resId) {
        vsLeft.id(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.apply();
            }
        });
        return this;
    }

    public ContentActionBar hide() {
        return hide(null);
    }

    public ContentActionBar hide(Animation animation) {
        final FrameLayout actionBarLayout = ((MoldActivity) getActivity()).getActionBarLayout();
        if (actionBarLayout.getVisibility() == View.VISIBLE) {
            if (animation != null) {
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        actionBarLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                actionBarLayout.startAnimation(animation);
            }
        }
        return this;
    }

    public ContentActionBar show() {
        return show(null);
    }

    public ContentActionBar show(Animation animation) {

        final FrameLayout actionBarLayout = ((MoldActivity) getActivity()).getActionBarLayout();
        if (actionBarLayout.getVisibility() == View.GONE) {
            if (animation != null) {
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        actionBarLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                actionBarLayout.startAnimation(animation);
            }
        }
        return this;
    }

    public ContentActionBar setBackgroundRes(int resId) {
        getActionBarLayout().setBackgroundResource(resId);
        return this;
    }

    public ContentActionBar setBackgroundDraw(Drawable draw) {
        getActionBarLayout().setBackground(draw);
        return this;
    }

    public ContentActionBar setBackgroundColor(int color) {
        getActionBarLayout().setBackgroundColor(color);
        return this;
    }

    private FrameLayout getActionBarLayout() {
        vsRoot.id(R.id.line).setVisibility(View.GONE);
        MoldActivity moldActivity = MoldUtil.getMoldActivity(getActivity());
        return moldActivity.getActionBarLayout();
    }

}
