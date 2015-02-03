package uz.greenwhite.slidingmenu.support.v10.common.fragment;

import android.view.View;

public interface SupportListImpl<E, H> {

    int adapterGetLayoutResource();

    H adapterMakeHolder(View view);

    void adapterPopulate(H holder, E item);
}
