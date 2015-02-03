package uz.greenwhite.slidingmenu.support.v9.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldUtil;

public abstract class IndexContentFragment<E, H> extends ContentFragment<E, H> {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MoldUtil.getMoldActivity(getActivity()).setContent(false);
    }

    @Override
    protected void setFlipper() {
        getActionBar().setDrawableFlipper(false, (float) 0.0);
    }

    protected void showIndexContent() {
        MoldUtil.getMoldActivity(getActivity()).showIndexContents();
    }
}
