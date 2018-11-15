package com.example.android.ssinventoryapp2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.ssinventoryapp2.Data.InventoryContract;
import com.example.android.ssinventoryapp2.Data.InventoryDbHelper;

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the product name
     */
    private EditText iNameEditText;
    /**
     * EditText field to enter the product price
     */
    private EditText iPriceEditText;
    /**
     * EditText field to enter the quantity
     */
    private EditText iQuantityEditText;

    private EditText iSupplierPhoneNumberEditText;

    private EditText iSupplierNameEditText;

    private Spinner iDietSpinner;

    private int iDiet = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        iNameEditText = (EditText) findViewById(R.id.edit_name);
        iPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        iQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        iSupplierNameEditText = (EditText) findViewById(R.id.edit_supplier_name);
        iSupplierPhoneNumberEditText = (EditText) findViewById(R.id.edit_supplier_phone_number);
        iDietSpinner = (Spinner) findViewById(R.id.spinner_diet);

        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter dietSpinnerAdapter = ArrayAdapter.createFromResource
                (this, R.array.array_diet_options, android.R.layout.simple_spinner_item);


        dietSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        iDietSpinner.setAdapter(dietSpinnerAdapter);


        iDietSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Diet")) {
                        iDiet = InventoryContract.InventoryEntry.TYPE_DIET;
                    } else if (selection.equals("Regular")) {
                        iDiet = InventoryContract.InventoryEntry.TYPE_REGULAR;
                    } else {
                        iDiet = InventoryContract.InventoryEntry.NOT_APPLICABLE;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iDiet = 0;
            }
        });

    }
    private void insertItem() {
        String nameString = iNameEditText.getText().toString().trim();
        String quantityString = iQuantityEditText.getText().toString().trim();
        String priceString = iPriceEditText.getText().toString().trim();
        String supplierNameString = iSupplierNameEditText.getText().toString().trim();
        String supplierPhoneNumberString = iSupplierPhoneNumberEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        int quantity = Integer.parseInt(quantityString);
        String typeString = iDietSpinner.getSelectedItem().toString();

        InventoryDbHelper inventoryDbHelper = new InventoryDbHelper(this);

        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(InventoryContract.InventoryEntry.COLUMN_NAME, nameString);
        values.put(InventoryContract.InventoryEntry.COLUMN_TYPE, iDiet);
        values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, quantity);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRICE, price);
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhoneNumberString);

        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving item", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Item saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertItem();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
