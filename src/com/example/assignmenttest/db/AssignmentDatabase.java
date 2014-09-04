package com.example.assignmenttest.db;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignmenttest.helper.Product;
import com.example.assignmenttest.helper.Store;
import com.example.assignmenttest.keys.KeysInteger;
import com.example.assignmenttest.keys.KeysString;

public class AssignmentDatabase extends SQLiteOpenHelper {

	public KeysString keysStringObj;

	public AssignmentDatabase(Context context, KeysString keyStringObj) {
		super(context, keyStringObj.DATABASENAME, null,
				KeysInteger.DATABASE_VERSION);
		this.keysStringObj = keyStringObj;
	}// end of constructor of database creation

	@Override
	public void onCreate(SQLiteDatabase sqldatabase) {
		sqldatabase.execSQL(keysStringObj.CREATE_QUERY_PRODUCT_TABLE);
		sqldatabase.execSQL(keysStringObj.CREATE_QUERY_STORE_TABLE);
		Log.d("Database on created", "Database table created");
	}// end of oncreate database created

	@Override
	public void onUpgrade(SQLiteDatabase sqldatabase, int oldVersion,
			int newVersion) {

		sqldatabase.execSQL(keysStringObj.DROP_QUERY_PRODUCT_TABLE);
		sqldatabase.execSQL(keysStringObj.DROP_QUERY_STORE_TABLE);
	}// end of onupgraded database function

	public boolean insertProductData(Product productobj) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues productobjValues = new ContentValues();
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_ID,
				productobj.getProductId());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_NAME,
				productobj.getProductName());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_DESCRIPTION,
				productobj.getProductDescription());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_ImageURL,
				productobj.getProductPhotoUrl());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_SALEPRICE,
				productobj.getProductSalePrice());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_REGPRICE,
				productobj.getProductRegularPrice());
		String colors = "";
		for (int i = 0; i < productobj.getColors().size(); i++) {
			colors = colors + productobj.getColors().get(i) + ";";
		}// end of for and this for is to set all colour in one text string with
			// separater ';'

		productobjValues.put(keysStringObj.PRODUCT_COLUMN_COLORS, colors);
		long res = db.insert(keysStringObj.KEY_PRODUCT_TABLE_NAME, null,
				productobjValues);
		productobjValues.clear();
		if (res == -1) {

			return false;
		}
		// now insert store values in store table
		for (int i = 0; i < productobj.getStores().size(); i++) {
			Store storeobj = productobj.getStores().get(i);
			productobjValues.put(keysStringObj.STORE_COLUMN_ID,
					storeobj.getStoreId());
			productobjValues.put(keysStringObj.STORE_COLUMN_NAME,
					storeobj.getStoreName());
			productobjValues.put(keysStringObj.PRODUCT_COLUMN_ID,
					productobj.getProductId());
			res = db.insert(keysStringObj.KEY_STORE_TABLE_NAME, null,
					productobjValues);
			if (res == -1) {
				// if error occur then al record will be
				// roll back
				return false;
			}
			productobjValues.clear();
		}// end of for to insert store values

		db.close();
		return true;
	}// end of insert productobj

	public void deleteProductData(String productId) {
		SQLiteDatabase db = this.getWritableDatabase();
		String deleteQueryProduct = "delete from "
				+ keysStringObj.KEY_PRODUCT_TABLE_NAME + " where "
				+ keysStringObj.PRODUCT_COLUMN_ID + "=\"" + productId + "\"";
		String deleteQueryStore = "delete from "
				+ keysStringObj.KEY_STORE_TABLE_NAME + " where "
				+ keysStringObj.PRODUCT_COLUMN_ID + "=\"" + productId + "\"";
		db.execSQL(deleteQueryProduct);
		db.execSQL(deleteQueryStore);
		db.close();
	}// end of deletion of data

	public ArrayList<Product> selectProductData() {
		ArrayList<Product> products = new ArrayList<Product>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectProductQuery = "select * from "
				+ keysStringObj.KEY_PRODUCT_TABLE_NAME;

		Cursor productsCursorObj = db.rawQuery(selectProductQuery, null);
		if (productsCursorObj.moveToFirst()) {
			do {
				String pId = productsCursorObj.getString(0);
				String pName = productsCursorObj.getString(1);
				String pDescription = productsCursorObj.getString(2);
				String pimageurl = productsCursorObj.getString(3);
				String psalePrice = productsCursorObj.getString(4);
				String pregularPrice = productsCursorObj.getString(5);
				String[] coloursArray = productsCursorObj.getString(6).split(
						";");
				ArrayList<String> pcolours = new ArrayList<String>(
						Arrays.asList(coloursArray));
				String selectStoreQuery = "select * from "
						+ keysStringObj.KEY_STORE_TABLE_NAME + " where "
						+ keysStringObj.PRODUCT_COLUMN_ID + "= \"" + pId
						+ "\";";
				Cursor storeCursorObj = db.rawQuery(selectStoreQuery, null);
				ArrayList<Store> stores = new ArrayList<Store>();
				if (storeCursorObj.moveToFirst()) {
					do {
						int sId = storeCursorObj.getInt(0);
						String sName = storeCursorObj.getString(1);
						stores.add(new Store(sId, sName));
					} while (storeCursorObj.moveToNext());
				}// this if is for getting all record of stores of one products
				products.add(new Product(pId, pName, pDescription,
						pregularPrice, psalePrice, pimageurl, pcolours, stores));
			} while (productsCursorObj.moveToNext());
		}// end of if for reading all data and putting in arraylist of products

		db.close();
		return products;
	}// end of select all data from database

	public void updateProductData(Product productObj) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues productobjValues = new ContentValues();
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_ID,
				productObj.getProductId());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_NAME,
				productObj.getProductName());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_DESCRIPTION,
				productObj.getProductDescription());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_ImageURL,
				productObj.getProductPhotoUrl());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_SALEPRICE,
				productObj.getProductSalePrice());
		productobjValues.put(keysStringObj.PRODUCT_COLUMN_REGPRICE,
				productObj.getProductRegularPrice());
		String colors = "";
		for (int i = 0; i < productObj.getColors().size(); i++) {
			colors = colors + productObj.getColors().get(i) + ";";
		}// end of for and this for is to set all colour in one text string with
			// separater ';'

		productobjValues.put(keysStringObj.PRODUCT_COLUMN_COLORS, colors);
		db.update(
				keysStringObj.KEY_PRODUCT_TABLE_NAME,
				productobjValues,
				keysStringObj.PRODUCT_COLUMN_ID + "=\""
						+ productObj.getProductId() + "\"", null);
		productobjValues.clear();
		// now insert store values in store table
		for (int i = 0; i < productObj.getStores().size(); i++) {
			Store storeobj = productObj.getStores().get(i);
			productobjValues.put(keysStringObj.STORE_COLUMN_ID,
					storeobj.getStoreId());
			productobjValues.put(keysStringObj.STORE_COLUMN_NAME,
					storeobj.getStoreName());
			productobjValues.put(keysStringObj.PRODUCT_COLUMN_ID,
					productObj.getProductId());

			db.update(
					keysStringObj.KEY_STORE_TABLE_NAME,
					productobjValues,
					keysStringObj.PRODUCT_COLUMN_ID + "=\""
							+ productObj.getProductId() + "\"", null);
			productobjValues.clear();
		}// end of for to insert store values

		db.close();
	}// end of updateproduct record

}// end of database class