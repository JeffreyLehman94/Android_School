package major_assignment_1.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jeff on 12/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "data_table";
    public static final String COL_0 = "array0";
    public static final String COL_1 = "array1";
    public static final String COL_2 = "array2";
    public static final String COL_3 = "array3";
    public static final String COL_4 = "array4";
    public static final String COL_5 = "array5";
    public static final String COL_6 = "array6";
    public static final String COL_7 = "array7";
    public static final String COL_8 = "array8";
    public static final String COL_9 = "game_status";
    public static final String COL_10 = "turn_number";
    public static final String COL_11 = "game_over";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(array0 TEXT, array1 TEXT, array2 TEXT, array3 TEXT, array4 TEXT, array5 TEXT, array6 TEXT, array7 TEXT, array8 TEXT, game_status TEXT, turn_number INTEGER, game_over TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newT) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String[] array, String status, int number, boolean gameOver) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        String s="false";
        if(gameOver){
            s="true";
        }
        contentValues.put(COL_0, array[0]);
        contentValues.put(COL_1, array[1]);
        contentValues.put(COL_2, array[2]);
        contentValues.put(COL_3, array[3]);
        contentValues.put(COL_4, array[4]);
        contentValues.put(COL_5, array[5]);
        contentValues.put(COL_6, array[6]);
        contentValues.put(COL_7, array[7]);
        contentValues.put(COL_8, array[8]);
        contentValues.put(COL_9, status);
        contentValues.put(COL_10, number);
        contentValues.put(COL_11, s);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {

            return false;
        } else {

            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}
