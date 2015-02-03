package uz.greenwhite.slidingmenu.support.v10.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Database.TAG, "DB:Creating databse");
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Database.TAG, "DB:Upgrading database from version " + oldVersion + " to " + newVersion);
        createTables(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Database.TAG, "DB:Downgrading database from version " + oldVersion + " to " + newVersion);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(TableRef.genDDL());
    }
}
