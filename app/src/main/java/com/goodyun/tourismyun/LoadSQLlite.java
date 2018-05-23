package com.goodyun.tourismyun;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LoadSQLlite extends SQLiteOpenHelper {


    String tableName = "recentitem";

    public LoadSQLlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE if not exists " + tableName + " (_no integer primary key AUTOINCREMENT,id text,img text not null,type integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void insert(String id, String img, int type) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + tableName + " Values(null,'" + id + "','" + img + "'," + type + ");");
        db.close();
    }

    ArrayList<RecentItem> items = new ArrayList<>();

    public ArrayList<RecentItem> getResult() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        RecentItem item;


        while (cursor.moveToNext()) {
            item = new RecentItem();

            item.setNo(cursor.getInt(0));
            item.setId(cursor.getString(1));
            item.setImg(cursor.getString(2));
            item.setType( cursor.getInt(3));
            items.add(item);
            item = null;

        }

        return items;
    }//getResult


    public void delete(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+tableName);
        db.close();
    }


}
