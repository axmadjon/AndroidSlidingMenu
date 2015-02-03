package uz.greenwhite.slidingmenu.support.v10.util;

public final class Util {
    private Util() {
    }
    //******************************************************************************************************************

    public static <V> V nvl(V val, V defVal) {
        return val == null ? defVal : val;
    }
}
