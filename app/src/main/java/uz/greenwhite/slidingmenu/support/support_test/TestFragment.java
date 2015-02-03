package uz.greenwhite.slidingmenu.support.support_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.widget.ViewSetup;

public class TestFragment extends SupportFragment {

    @Override
    protected ViewSetup onCreateViewSetup(@Nullable LayoutInflater inflater, @Nullable ViewGroup container) {
        return new ViewSetup(inflater, container, R.layout.test_support_content);
    }

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("Hello");
        getVsRoot().button(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        for (int i = 0; i < 10; i++) {

        }

    }
}
