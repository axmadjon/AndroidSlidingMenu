package uz.greenwhite.slidingmenu.support.v10.common.mold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import uz.greenwhite.slidingmenu.support.v10.SupportApplication;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.error.SystemError;
import uz.greenwhite.slidingmenu.support.v10.service.TaskGroup;
import uz.greenwhite.slidingmenu.support.v10.service.TaskManager;
import uz.greenwhite.slidingmenu.support.v10.service.TaskService;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.FragmentLife;
import uz.greenwhite.slidingmenu.support.v10.service.life_cycle.LifeCycle;
import uz.greenwhite.slidingmenu.support.v10.service.request.TaskRequest;

import java.util.List;

class MyActivity extends ActionBarActivity {

    public static final String ARG_CLASS = "uz.greenwhite.slidingmenu.support_v10.lib.mac";
    public static final String ARG_BUNDLE = "uz.greenwhite.slidingmenu.support_v10.lib.mab";
    public static final String ARG_FULL = "uz.greenwhite.slidingmenu.support_v10.lib.maf";
    public static final String ARG_DATA = "uz.greenwhite.slidingmenu.support_v10.lib.data";

    public static Intent open(Context ctx, Class<? extends Fragment> cls, Bundle args, boolean fullDisplay) {
        Intent intent = new Intent(ctx, SupportActivity.class);
        intent.putExtra(ARG_CLASS, cls);
        intent.putExtra(ARG_FULL, fullDisplay);
        if (args != null) {
            intent.putExtra(ARG_BUNDLE, args);
        }
        return intent;
    }

    public static Intent openFullDisplay(Activity activity, Class<? extends SupportFragment> cls, Bundle args) {
        return open(activity, cls, args, true);
    }

    public static Intent open(Activity activity, Class<? extends SupportFragment> cls, Bundle args) {
        return open(activity, cls, args, false);
    }


}
