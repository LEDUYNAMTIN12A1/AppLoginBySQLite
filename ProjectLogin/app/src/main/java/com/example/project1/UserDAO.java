package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
    SQLiteDatabase database;
    public UserDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }
    public long ThemUser(UserDTO UserDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_USER_MATKHAU,UserDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TBL_USER_SDT,UserDTO.getSDT());
        long ktra = database.insert(CreateDatabase.TBL_USER,null,contentValues);
        return ktra;
    }
}
