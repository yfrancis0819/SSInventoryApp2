package com.example.android.ssinventoryapp2.Data;

import android.provider.BaseColumns;

public class InventoryContract {

    public static abstract class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME ="Inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "Product Name";
        public static final String COLUMN_TYPE = "Diet or Regular";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "Supplier Name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "Supplier Phone Number";

        public static final int TYPE_DIET = 1;
        public static final int TYPE_REGULAR = 2;
        public static final int NOT_APPLICABLE = 0;



    }}
