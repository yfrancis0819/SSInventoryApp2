package com.example.android.ssinventoryapp2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.ssinventoryapp2.Data.InventoryContract;
import com.example.android.ssinventoryapp2.Data.InventoryDbHelper;

public class InventoryActivity extends AppCompatActivity {
    private InventoryDbHelper iDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //**Set up a button to open the editor activity*/

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (InventoryActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        iDbHelper = new InventoryDbHelper(this);

    }
    private void displayDatabaseInfo() {
        InventoryDbHelper iDbHelper = new InventoryDbHelper(this);

        SQLiteDatabase db = iDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_NAME,
                InventoryContract.InventoryEntry.COLUMN_TYPE,
                InventoryContract.InventoryEntry.COLUMN_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_PRICE,
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME, projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_inventory);

        try {


            displayView.setText("The inventory table contains: " + cursor.getCount() + " items.\n\n");
            displayView.append(InventoryContract.InventoryEntry._ID + " = "
                    + "-" + InventoryContract.InventoryEntry.COLUMN_NAME
                    + "-" + InventoryContract.InventoryEntry.COLUMN_TYPE
                    + "-" + InventoryContract.InventoryEntry.COLUMN_QUANTITY
                    + "-" + InventoryContract.InventoryEntry.COLUMN_PRICE
                    + "-" + InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME
                    + "-" + InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");


            int idColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry._ID);
            int idColumnName = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_NAME);
            int idColumnType = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_TYPE);
            int idColumnQuantity = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY);
            int idColumnPrice = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE);
            int idColumnSupplierName = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME);
            int idColumnSupplierPhoneNumber = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            while(cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                int currentName = cursor.getInt(idColumnName);
                int currentType = cursor.getInt(idColumnType);
                int currentQuantity = cursor.getInt(idColumnQuantity);
                int currentPrice = cursor.getInt(idColumnPrice);
                int currentSupplierName = cursor.getInt(idColumnSupplierName);
                int currentSupplierPhoneNumber = cursor.getInt(idColumnSupplierPhoneNumber);

                displayView.append(("\n" + currentID + " = "
                        + currentName
                        + "-" + currentType
                        + "-" + currentQuantity
                        + "-" + currentPrice
                        + "-" + currentSupplierName
                        + "-" + currentSupplierPhoneNumber));
            }
        } finally {
            cursor.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }
    private void insertItem(){
        SQLiteDatabase db = iDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_NAME, "Hersheys");
        values.put(InventoryContract.InventoryEntry.COLUMN_PRICE, 1);
        values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, 1);
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME, "Sam's Club");
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "609-233-7806");

        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        Log.v("InventoryActivity", "New Row ID" + newRowId);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertItem();
                displayDatabaseInfo();
                return true;
            //Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

