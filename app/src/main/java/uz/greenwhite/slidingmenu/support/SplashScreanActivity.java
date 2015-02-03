package uz.greenwhite.slidingmenu.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import uz.greenwhite.slidingmenu.support.support_test.TestFragment;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;

public class SplashScreanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent open = SupportFragment.open(this, TestFragment.class);
        startActivity(open);
    }
}
