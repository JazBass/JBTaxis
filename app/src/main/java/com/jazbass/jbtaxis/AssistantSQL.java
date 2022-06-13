package com.jazbass.jbtaxis;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AssistantSQL extends SQLiteOpenHelper {

    SQLiteDatabase database;
    public AssistantSQL(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        assert context != null;
        database = context.openOrCreateDatabase(name,Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE history (date datetime," +
                "carRegistration char(7)," +
                "name char (120)," +
                "surname char (120))";
        try {
            database.execSQL(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newRegist(String carRegistration, String name, String surname){
        String sql = "INSERT INTO history (date, carRegistration, name, surname)" +
                "values (CURRENT_TIMESTAMP, '"+carRegistration + "', '" + name + "', '"+ surname+"')";
        database.execSQL(sql);
    }

    public ArrayList<String[]> getHistory(){
        String sql = "SELECT * FROM history";
        Cursor cursor = database.rawQuery("SELECT * FROM history ORDER BY Date DESC", null);
        ArrayList<String[]> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(new String[]{
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)});
            cursor.moveToNext();
        }
        return arrayList;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
