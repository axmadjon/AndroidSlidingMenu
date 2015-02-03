package uz.greenwhite.slidingmenu.support.v10.common.fragment;

import android.view.View;
import uz.greenwhite.slidingmenu.support.v10.common.Command;

public class SupportMenuContainer {

    public final int iconResId;
    public final Command command;
    public final View view;
    public int id = 0;

    public SupportMenuContainer(int iconResId, Command command) {
        this.iconResId = iconResId;
        this.command = command;
        this.view = null;
    }

    public SupportMenuContainer(View view) {
        this.iconResId = 0;
        this.command = null;
        this.view = view;
    }


}
