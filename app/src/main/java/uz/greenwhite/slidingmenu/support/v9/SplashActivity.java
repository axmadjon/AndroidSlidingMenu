package uz.greenwhite.slidingmenu.support.v9;

import android.app.Activity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestIndexFragment.open(this);
    }
}