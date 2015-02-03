package uz.greenwhite.slidingmenu.support.v10.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v10.json.JsonAdapter;
import uz.greenwhite.slidingmenu.support.v10.json.JsonInput;


public class TableRef {

    private SQLiteDatabase db;

    public TableRef(SQLiteDatabase db) {
        this.db = db;
    }

    public String loadOne(String code, String id) {
        String query = "SELECT val FROM oc_ref WHERE code = ? AND id = ?";
        return CursorUtil.loadOne(db, CursorUtil.ONLY_STRING, query, code, id);
    }

    public int count(String code) {
        String query = "SELECT count(*) FROM oc_ref WHERE code = ?";
        return CursorUtil.loadOne(db, CursorUtil.ONLY_INTEGER, query, code);
    }

    public <T> T loadOne(String code, String id, final JsonAdapter<T> jsonAdapter) {
        String s = loadOne(code, id);
        if (s != null) {
            return JsonInput.parse(s, jsonAdapter);
        }
        return null;
    }

    public <T> MyArray<T> loadAll(String code, final JsonAdapter<T> jsonAdapter) {
        String query = "SELECT val FROM oc_ref WHERE code = ?";
        CursorUtil<T> c = new CursorUtil<T>() {
            @Override
            public T apply(Cursor c) {
                return JsonInput.parse(c.getString(0), jsonAdapter);
            }
        };
        return CursorUtil.loadAll(db, c, query, code);
    }

    public MyArray<String> loadIds(String code) {
        String query = "SELECT id FROM oc_ref WHERE code = ?";
        return CursorUtil.loadAll(db, CursorUtil.ONLY_STRING, query, code);
    }

    public MyArray<String> loadCodeAndIds() {
        String query = "SELECT '\"' || code || '\":[' || GROUP_CONCAT('\"' || id || '\"') || ']' "
                + "FROM oc_ref GROUP BY code";
        return CursorUtil.loadAll(db, CursorUtil.ONLY_STRING, query);
    }

    public void replace(String code, String id, String val) {
        String query = "INSERT OR REPLACE INTO oc_ref(code, id, val) VALUES (?,?,?)";
        db.execSQL(query, new Object[]{code, id, val});
    }

    public void delete(String code, String id) {
        String query = "DELETE FROM oc_ref WHERE code = ? AND id = ?";
        db.execSQL(query, new Object[]{code, id});
    }

    public void delete(String code) {
        String query = "DELETE FROM oc_ref WHERE code = ?";
        db.execSQL(query, new Object[]{code});
    }

    public static String genDDL() {
        return new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS oc_ref(")
                .append("code TEXT NOT NULL,")
                .append("id TEXT NOT NULL,")
                .append("val TEXT NOT NULL,")
                .append("PRIMARY KEY(code, id))")
                .toString();
    }
}
