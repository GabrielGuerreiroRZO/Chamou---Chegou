package com.example.chamouchegou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

public class BancoHelper extends SQLiteOpenHelper {
    private  static  final  String TAG = "BalcoHelper";
    private static final String TABLE_NAME = "usuario_tabela";
    private static final  String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "bairro";
    private static final String COL4 = "apelido";
    Context context;


    public BancoHelper(Context context) {
        super(context,TABLE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = "CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, bairro TEXT, apelido TEXT)";
        db.execSQL(creatTable);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE  " + TABLE_NAME);
        onCreate(db);
    }

    public  boolean addData (String name, String bairro, String apelido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, bairro);
        contentValues.put(COL4, apelido);

         long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }

    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;

    }


    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + "SET" + COL2 + " = '" + newName + " WHERE " + COL1 + " = " + id + " ' " + " AND " + COL2 + " = '" + oldName + " ' ";
        Log.d(TAG, "updateName: query " + query);
        Log.d(TAG,"updateName:  Setting name to " + newName);
        db.execSQL(query);
    }

    public void deleteName(int id, String name,String bairro, String apelido){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + " ' " + " AND " + COL2 + " = '" + name + "AND"
                + COL3 + " = '" + bairro + " ' " + " AND " + COL4 + " = '" + apelido + "'";
        Log.d(TAG, "deleteName: query" + query);
        Log.d(TAG,"deleteName: Deleting" + name + " from databse.");
        db.execSQL(query);
    }


}
