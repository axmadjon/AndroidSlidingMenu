package uz.greenwhite.slidingmenu.support.v9.common.mold;

import android.app.Activity;
import uz.greenwhite.slidingmenu.support.v9.common.ContentFragment;
import uz.greenwhite.slidingmenu.support.v9.common.IndexFragment;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.ContentActionBar;

public final class MoldUtil {

    public static MoldActivity getMoldActivity(Activity a) {
        return (MoldActivity) a;
    }

    public static void setActionBar(Activity activity, ContentActionBar actionBar) {
        getMoldActivity(activity).setActionBar(actionBar);
    }

    public static ContentFragment getContentFragment(Activity activity) {
        return getMoldActivity(activity).getContentFragment();
    }

    public static IndexFragment getIndexFragment(Activity activity) {
        return getMoldActivity(activity).getIndexContent();
    }

    public static void replaceContent(Activity activity, ContentFragment cf) {
        getMoldActivity(activity).replaceContent(cf);
    }

    public static void addContent(Activity activity, ContentFragment cf) {
        getMoldActivity(activity).addContent(cf);
    }

    public static void openContent(Activity activity, ContentFragment cf) {
        getMoldActivity(activity).openContent(cf);
    }

    private MoldUtil() {
    }
}
