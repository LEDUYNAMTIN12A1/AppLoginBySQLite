package com.example.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public static String TBL_USER = "USER";

    public static String TBL_USER_MATKHAU = "MATKHAU";
    public static String TBL_USER_SDT = "SDT";

    public CreateDatabase(Context context) {
        super(context, "ProjectLogin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblNHANVIEN = "CREATE TABLE " +TBL_USER+ " ( " +TBL_USER_SDT+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                 +TBL_USER_MATKHAU+ "INTEGER)";
        db.execSQL(tblNHANVIEN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
