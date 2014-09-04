package com.example.assignmenttest.keys;

public class KeysString { // this class hold string values which is mostly
							// reused in application

	public String Host_Url = "http://www.json-generator.com/api/json/get/cfdTXlFWqa?indent=2";
	public String DATABASENAME = "assignmentdatabase";

	// ****************************************************************Product
	// table database data
	public String KEY_PRODUCT_TABLE_NAME = "producttable";
	public String PRODUCT_COLUMN_ID = "pid";
	public String PRODUCT_COLUMN_NAME = "pname";
	public String PRODUCT_COLUMN_DESCRIPTION = "pdescription";
	public String PRODUCT_COLUMN_ImageURL = "imageurl";
	public String PRODUCT_COLUMN_COLORS = "pcolours";
	public String PRODUCT_COLUMN_REGPRICE = "regularprice";
	public String PRODUCT_COLUMN_SALEPRICE = "saleprice";

	public String CREATE_QUERY_PRODUCT_TABLE = "CREATE TABLE "
			+ KEY_PRODUCT_TABLE_NAME + " ( " + PRODUCT_COLUMN_ID
			+ " TEXT PRIMARY KEY," + PRODUCT_COLUMN_NAME + " TEXT,"
			+ PRODUCT_COLUMN_DESCRIPTION + " TEXT," + PRODUCT_COLUMN_ImageURL
			+ " TEXT," + PRODUCT_COLUMN_SALEPRICE + " TEXT,"
			+ PRODUCT_COLUMN_REGPRICE + " TEXT," + PRODUCT_COLUMN_COLORS
			+ " TEXT);";
	public String DROP_QUERY_PRODUCT_TABLE = "drop table if exist "
			+ KEY_PRODUCT_TABLE_NAME + ";";

	// ****************************************************

	// ***************STORE TABLE DATABASE Query
	public String KEY_STORE_TABLE_NAME = "storetable";
	public String STORE_COLUMN_ID = "storeId";
	public String STORE_COLUMN_NAME = "storename";

	public String CREATE_QUERY_STORE_TABLE = "CREATE TABLE "
			+ KEY_STORE_TABLE_NAME + " (" + STORE_COLUMN_ID + " TEXT,"
			+ STORE_COLUMN_NAME + " TEXT," + PRODUCT_COLUMN_ID
			+ " TEXT REFERENCES " + KEY_PRODUCT_TABLE_NAME + "("
			+ PRODUCT_COLUMN_ID + "));";

	public String DROP_QUERY_STORE_TABLE = "drop table if exist "
			+ KEY_STORE_TABLE_NAME + ";";
	// *************************************
}// end of keyString class
