package uz.greenwhite.slidingmenu.support.v10.db;

import android.content.Context;

public class Database {
    public static final String TAG = "DB";

    public final TableRef ref;

    public Database(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context, "aylana.db");
        this.ref = new TableRef(helper.getWritableDatabase());
    }
}
