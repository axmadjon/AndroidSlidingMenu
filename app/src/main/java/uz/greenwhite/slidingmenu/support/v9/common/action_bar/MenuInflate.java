package uz.greenwhite.slidingmenu.support.v9.common.action_bar;

import java.util.ArrayList;
import java.util.List;

public class MenuInflate {
    public List<MenuItem> menuItems;

    public MenuInflate() {
        menuItems = new ArrayList<MenuItem>();
    }

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void clear() {
        menuItems.clear();
    }

    public int size() {
        return menuItems.size();
    }
}