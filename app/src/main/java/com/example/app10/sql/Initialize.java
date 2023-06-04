package com.example.app10.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Initialize extends SQLiteOpenHelper {
    public Initialize(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createArticles = "create table articles(" +
                "id integer primary key autoincrement not null," +
                "title text not null," +
                "date text not null," +
                "content text not null" +
                ")";
        sqLiteDatabase.execSQL(createArticles);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
