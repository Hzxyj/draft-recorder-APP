package com.example.app10.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class CRUD {
    public void addArticle(String title, String date, String content, SQLiteDatabase sqLiteDatabase){
        String sql="insert into articles values(null,?,?,?)";
        sqLiteDatabase.execSQL(sql,new String[]{title,date,content});
        sqLiteDatabase.close();
    }

    public ArrayList<Article> selectAll(SQLiteDatabase sqLiteDatabase){
        String sql="select * from articles";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        Log.d("cursor", String.valueOf(cursor.getCount()));
        ArrayList<Article> article= new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String id=cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String content=cursor.getString(cursor.getColumnIndexOrThrow("content"));
                article.add(new Article(id,title,date,content));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return article;
    }

    public ArrayList<Article> selectByDate(String dateQuery,SQLiteDatabase sqLiteDatabase){
        String sql="select * from articles where date=?";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,new String[]{dateQuery});
        Log.d("cursor", String.valueOf(cursor.getCount()));
        ArrayList<Article> article= new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String id=cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                article.add(new Article(id,title,date, null));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return article;
    }

    public Article selectById(String IdQuery,SQLiteDatabase sqLiteDatabase){
        String sql="select * from articles where id=?";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,new String[]{IdQuery});
        Article article=new Article();
        if(cursor.moveToFirst()){
                String id=cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String content=cursor.getString(cursor.getColumnIndexOrThrow("content"));
                article=new Article(id,title,date, content);
        }
        cursor.close();
        return article;
    }

    public void delete(String IdQuery,SQLiteDatabase sqLiteDatabase){
        String sql="delete from articles where id=?";
        sqLiteDatabase.execSQL(sql,new String[]{IdQuery});
    }
}
