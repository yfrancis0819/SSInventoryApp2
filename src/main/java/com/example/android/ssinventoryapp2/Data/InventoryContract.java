package com.example.android.ssinventoryapp2.Data;
import android.provider.BaseColumns;

public final class InventoryContract {
    private InventoryContract(){}

    public static abstract class InventoryEntry implements BaseColumns {


        public final static String TABLE_NAME ="inventory";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEM_NAME = "Item Name";
        public final static String COLUMN_ITEM_PRICE = "Price";
        public final static String COLUMN_ITEM_QUANTITY = "Quantity";
        public final static String COLUMN_ITEM_SUPPLIERNAME = "Supplier_Name";
        public final static String COLUMN_ITEM_SUPPLIERPHONENUMBER = "Supplier_Phone_Number";


    }}

