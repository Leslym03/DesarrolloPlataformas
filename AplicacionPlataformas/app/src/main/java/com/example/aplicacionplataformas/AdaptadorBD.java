package com.example.aplicacionplataformas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdaptadorBD extends SQLiteOpenHelper {

    public static final String TABLE_ID = "_ideNote";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    public static final String DATBASE = "Note";
    public static final String TABLE = "notes";


    public AdaptadorBD(Context context) {
        super(context, DATBASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT," + CONTENT + " TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addNote(String title,String content){
        ContentValues valores = new ContentValues();
        valores.put(TITLE,title);
        valores.put(CONTENT,content);
        this.getWritableDatabase().insert(TABLE,null,valores);
    }

    public Cursor getNote(String condition){
        String columnas[] = {TABLE_ID,TITLE,CONTENT};
        String[] args = new String[] {condition};
        Cursor c = this.getReadableDatabase().query(TABLE,columnas,TITLE+"=?", args, null, null,null);
        return c;


    }

    public void deleteNote(String condition){
        String args[] ={condition};
        this.getWritableDatabase().delete(TABLE,TITLE + "=?",args);
    }



}
