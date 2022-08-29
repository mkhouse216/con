package com.example.contact_list;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper  extends SQLiteOpenHelper {
    public Dbhelper(@Nullable Context context) {
        super(context, "demo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "create table ContactBook (id integer primary key autoincrement,Name Text, Number Text)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertdata(String name, String number) {

        String insert = "insert into ContactBook (Name,Number)   values('"+name+"','"+number+"')";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
    }

    public Cursor viewdata() {

        String select ="select * from ContactBook";
        SQLiteDatabase db =getReadableDatabase();

        Cursor cursor =db.rawQuery(select,null);
        return cursor;
    }


    public void deletedeta(int position) {
        int cc=position++;
        String delete ="delete from ContactBook where Id='"+cc+"'";
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL(delete);
    }

    public void onUpgrade1(String name, String number, int id) {


        String update ="update ContactBook set Name ='"+name+"', Number='"+number+"'where id='"+id+"'";
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL(update);

    }


}
