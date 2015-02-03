package uz.greenwhite.slidingmenu.support.v10.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;

import java.util.ArrayList;
import java.util.List;


public abstract class CursorUtil<R> {

    public abstract R apply(Cursor c);

    public static final CursorUtil<String> ONLY_STRING = new CursorUtil<String>() {
        @Override
        public String apply(Cursor c) {
            return c.getString(0);
        }
    };

    public static final CursorUtil<Integer> ONLY_INTEGER = new CursorUtil<Integer>() {
        @Override
        public Integer apply(Cursor c) {
            return c.getInt(0);
        }
    };

    public static <T> T loadOne(SQLiteDatabase db, CursorUtil<T> mapper,
                                String query, String... args) {
        MyArray<T> r = loadAll(db, mapper, query, args);
        switch (r.size()) {
            case 0:
                return null;
            case 1:
                return r.get(0);
            default:
                throw new NullPointerException("Too many rows");
        }
    }

    public static <T> MyArray<T> loadAll(SQLiteDatabase db, CursorUtil<T> mapper,
                                         String query, String... args) {
        Cursor c = null;
        try {
            c = db.rawQuery(query, args);
            List<T> result = new ArrayList<T>();

            if (c.moveToFirst()) {
                do {
                    result.add(mapper.apply(c));
                } while (c.moveToNext());
            }

            return MyArray.from(result);
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
    }
}