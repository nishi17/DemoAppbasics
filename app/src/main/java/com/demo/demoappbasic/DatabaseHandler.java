package com.demo.demoappbasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nishi on 4/13/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DAABASE_VERSION = 8;


    private static final String DAABASE_NAME = "demo.db";

    public static final String TABLE_PROFILE = "profile";


    public static final String P_ID = "id";
    public static final String P_FIRSTNAME = "firstname";
    public static final String P_LASTNAME = "lastname";
    public static final String P_EMAIL = "email";

    public static final String P_PASSWPORD = "password";


    public static final String CREATE_TABLE_PROFILE = " CREATE TABLE " + TABLE_PROFILE +
            " ( " +
            P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            P_FIRSTNAME + " TEXT NOT NULL, " +
            P_LASTNAME + " TEXT NOT NULL, " +
            P_EMAIL + " TEXT UNIQUE NOT NULL, " +
            P_PASSWPORD + " TEXT NOT NULL " +
            " ) ";


    public static final String TABLE_AREA = "area";

    public static final String A_ID = "id";
    public static final String A_CITY = "city";
    public static final String A_STATE = "state";
    public static final String A_COUNTRY = "country";
    public static final String A_P_ID = "a_p_id";

    public static final String CREATE_TABLE_AREA = " CREATE TABLE " + TABLE_AREA +
            " ( " +
            A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            A_CITY + " TEXT NOT NULL, " +
            A_STATE + " TEXT NOT NULL, " +
            A_COUNTRY + " TEXT NOT NULL, " +
            A_P_ID + " INTEGER NOT NULL," +
            " FOREIGN KEY ( " + A_P_ID + " ) REFERENCES " + TABLE_PROFILE + " ( " + P_ID + " ) " +

            " ) ";

    public DatabaseHandler(Context context) {
        super(context, DAABASE_NAME, null, DAABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_AREA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onCreate(db);

        //DBUpdation.tableUpgrade(db ,TABLE_PROFILE ,CREATE_TABLE_PROFILE );
        //DBUpdation.tableUpgrade(db ,TABLE_AREA ,CREATE_TABLE_AREA );

      //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_AREA);

       // onCreate(db);

        DBUpdation.tableUpgrade(db, TABLE_PROFILE, CREATE_TABLE_PROFILE);
        DBUpdation.tableUpgrade(db, TABLE_AREA, CREATE_TABLE_AREA);
    }
}
