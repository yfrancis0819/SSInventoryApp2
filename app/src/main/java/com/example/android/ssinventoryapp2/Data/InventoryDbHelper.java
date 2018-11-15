package com.example.android.ssinventoryapp2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.ssinventoryapp2.R;

public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    public InventoryDbHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_INVENTORY_TABLE =  "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " ("
                + InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryContract.InventoryEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_TYPE + " INTEGER NOT NULL DEFAULT 0, "
                + InventoryContract.InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int OldVersion, int NewVersion){
    }

}