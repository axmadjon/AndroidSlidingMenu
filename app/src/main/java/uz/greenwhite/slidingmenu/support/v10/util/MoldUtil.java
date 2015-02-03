package uz.greenwhite.slidingmenu.support.v10.util;

import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.common.mold.SupportActivity;

public final class MoldUtil {
    private MoldUtil() {
    }

    //**********************************************************************************************

    public static void popContent(SupportActivity activity) {
        popContent(activity, null);
    }

    public static void popContent(SupportActivity activity, Object obj) {
        activity.popContent(obj);
    }

    public static void addContent(SupportActivity activity, SupportFragment supportFragment) {
        activity.addContent(supportFragment);
    }

    public static void replaceContent(SupportActivity activity, SupportFragment supportFragment) {
        activity.replaceContent(supportFragment);
    }
}
