package com.example.assignmenttest.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;

import com.example.assignmenttest.R;
import com.example.assignmenttest.customadopter.SetListAdapter;
import com.example.assignmenttest.db.AssignmentDatabase;
import com.example.assignmenttest.handler.PixelResolverHander;
import com.example.assignmenttest.handler.Utils;
import com.example.assignmenttest.helper.Product;
import com.example.assignmenttest.keys.KeysString;
import com.example.assignmenttest.resources.CreateProductResources;
import com.example.assignmenttest.resources.HomeResources;

public class CreateProductActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	boolean isCreateActivity = false;
	KeysString keyStringObj;
	SetListAdapter listAdapter;
	ArrayList<Product> productArray;
	CreateProductResources resourcesObj;
	AssignmentDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_product);
		init();
	}// end of create function

	private void init() {
		// TODO Auto-generated method stub
		keyStringObj = new KeysString();
		Utils.context = this;
		PixelResolverHander.initPixelResolverInstance(this);
		resourcesObj = new CreateProductResources(this);
		Intent intentObj = getIntent();
		Bundle bundleObj = intentObj.getExtras();
		isCreateActivity = bundleObj.getBoolean("ActivityType");
		database = new AssignmentDatabase(this, keyStringObj);
		productArray = HomeResources.productsArray;

		if (!isCreateActivity) {
			resourcesObj.list_products
					.setOnItemClickListener((OnItemClickListener) this);
		}
	}// end of init function

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_homePageRevert:
			this.finish();
			break;
		
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!isCreateActivity) {
			productArray = database.selectProductData();
		}
		listAdapter = new SetListAdapter(this, productArray, isCreateActivity);
		resourcesObj.list_products.setAdapter(listAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(CreateProductActivity.this,
				ProductDetailActivity.class);
		intent.putExtra("ID", position);
		startActivity(intent);

	}

}// end of CreateProduct Activity
