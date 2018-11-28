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
import com.example.android.ssinventoryapp2.Data.InventoryContract.InventoryEntry;

import com.example.android.ssinventoryapp2.Data.InventoryContract;
import com.example.android.ssinventoryapp2.Data.InventoryDbHelper;

import org.w3c.dom.Text;

public class InventoryActivity extends AppCompatActivity {
    private InventoryDbHelper mDbHelper;

    public static final String LOG_TAG = InventoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //**Set up a button to open the editor activity*/

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new InventoryDbHelper(this);
      displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + InventoryEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_inventory);
            displayView.setText("Number of rows in inventory database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

          private void insertitem() {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

             ContentValues values = new ContentValues();

               values.put(InventoryEntry.COLUMN_ITEM_NAME, "Fritos");
               values.put(InventoryEntry.COLUMN_ITEM_PRICE, 1);
               values.put(InventoryEntry.COLUMN_ITEM_QUANTITY, 3);
               values.put(InventoryEntry.COLUMN_ITEM_SUPPLIERNAME, "Sam's");
               values.put(InventoryEntry.COLUMN_ITEM_SUPPLIERPHONENUMBER, "56789");

           long newRowID = db.insert(InventoryEntry.TABLE_NAME, null, values);


         }
    @Override
       public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {
                // Respond to a click on the "Insert dummy data" menu option
                case R.id.action_insert_dummy_data:
                  insertitem();
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

