package uz.greenwhite.slidingmenu.support.v10;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import uz.greenwhite.slidingmenu.support.v10.util.RequestUtil;

public abstract class SupportApplication extends Application {

    private static Context context;

    public static Boolean DEBUG;
    public static String VERSION;
    public static final String TAG = "Support";
    public static RequestUtil requestUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DEBUG = (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        VERSION = getVersion();
        requestUtil = new RequestUtil(context);
    }

    public static Context getContext() {
        return context;
    }

    private String getVersion() {

        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
