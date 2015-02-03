package uz.greenwhite.slidingmenu.support.v9.common.action_bar;

import uz.greenwhite.slidingmenu.support.v9.common.Command;
import uz.greenwhite.slidingmenu.support.v9.view_setup.ViewSetup;

public class MenuItem {
    public ViewSetup vs = null;
    public int iconRes = 0;
    public Command command = null;

    public MenuItem(int iconRes, Command command) {
        this.iconRes = iconRes;
        this.command = command;
    }

    public MenuItem(ViewSetup vs, Command command) {
        this.vs = vs;
        this.command = command;
    }
}