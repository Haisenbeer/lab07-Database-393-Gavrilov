package com.example.lab07_database_393_gavrilov;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper{
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "CREATE TABLE my_table (my_key TEXT, value TEXT);";
        db.execSQL(sql);
    }

    public void doInsert(String key, String value)
    {
        String sql = "INSERT INTO my_table VALUES('" + key + "','" + value + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String doSelect(String key)
    {
        String sql = "SELECT value FROM my_table WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "not found!";
    }

    public String doUpgrade(String key, String value)
    {
        String sql = "UPDATE my_table SET value = '" + value + "' WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "not found!";
    }

    public String doDelete(String key)
    {
        String sql = "DELETE FROM my_table WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "not found!";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
