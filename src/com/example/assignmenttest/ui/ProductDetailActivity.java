package com.example.assignmenttest.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignmenttest.R;
import com.example.assignmenttest.db.AssignmentDatabase;
import com.example.assignmenttest.handler.PixelResolverHander;
import com.example.assignmenttest.handler.Utils;
import com.example.assignmenttest.helper.Product;
import com.example.assignmenttest.keys.KeysString;
import com.example.assignmenttest.resources.HomeResources;
import com.example.assignmenttest.resources.ProductDetailResources;

public class ProductDetailActivity extends Activity implements OnClickListener {
	ProductDetailResources productDetailResourcesobj;
	Product productObj;
	Bitmap bitmapImageObj;
	ProgressDialog progressAuthenticating;
	EditText input;
	AlertDialog.Builder alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productdetail);
		init();

	}// end of oncreate method

	private void init() {
		// TODO Auto-generated method stub
		Utils.context = this;
		PixelResolverHander.initPixelResolverInstance(this);
		int position = getIntent().getExtras().getInt("ID");

		productObj = HomeResources.productsArray.get(position);
		productDetailResourcesobj = new ProductDetailResources(this, productObj);
		progressAuthenticating = new ProgressDialog(this);
		progressAuthenticating.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressAuthenticating.setIndeterminate(true);
		progressAuthenticating.setMessage("Loading please wait...");
		progressAuthenticating.setCancelable(true);
		new AuthenticateUser().execute(productObj.getProductPhotoUrl());
		alert = new AlertDialog.Builder(this);
	}// end of init function of activity

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (productDetailResourcesobj.image_product_fullsize.getVisibility() == ImageView.VISIBLE) {
			productDetailResourcesobj.image_product_fullsize
					.setVisibility(View.GONE);
			productDetailResourcesobj.container_product_data
					.setVisibility(View.VISIBLE);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_product:
			productDetailResourcesobj.image_product_fullsize
					.setVisibility(View.VISIBLE);
			productDetailResourcesobj.container_product_data
					.setVisibility(View.GONE);
			break;
		case R.id.btn_productpage_delete:
			AssignmentDatabase databaseObj = new AssignmentDatabase(
					ProductDetailActivity.this, new KeysString());
			databaseObj.deleteProductData(productObj.getProductId());
			Toast.makeText(this, "Product Deleted", Toast.LENGTH_LONG).show();
			this.finish();

			break;
		case R.id.btn_productpage_update:
			databaseObj = new AssignmentDatabase(ProductDetailActivity.this,
					new KeysString());
			databaseObj.updateProductData(productObj);
			Toast.makeText(this, "Product Updated", Toast.LENGTH_LONG).show();
			this.finish();

			break;

		case R.id.text_product_id:

			alert.setTitle("Product Id");
			alert.setMessage("Change the Product Id");

			// Set an EditText view to get user input
			input = new EditText(this);
			input.setText(productDetailResourcesobj.text_product_id.getText());
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							if (value.length() > 0) {
								productDetailResourcesobj.text_product_id
										.setText(value);
								productObj.setProductId(value);
							}
							// Do something with value!
						}
					});

			alert.show();

			break;
		case R.id.text_product_description:

			alert.setTitle("Product Description");
			alert.setMessage("Change the Product Description");

			// Set an EditText view to get user input
			input = new EditText(this);
			input.setText(productDetailResourcesobj.text_product_description
					.getText());
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							if (value.length() > 0) {
								productDetailResourcesobj.text_product_description
										.setText(value);
								productObj.setProductDescription(value);
							}
							// Do something with value!
						}
					});

			alert.show();
			break;
		case R.id.text_product_regularprice:
			alert.setTitle("Product Regular Price");
			alert.setMessage("Change the Product Regular Price");

			// Set an EditText view to get user input
			input = new EditText(this);
			input.setText(productDetailResourcesobj.text_product_regularprice
					.getText());
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							if (value.length() > 0) {
								productDetailResourcesobj.text_product_regularprice
										.setText(value);
								productObj.setProductRegularPrice(value);
							}
							// Do something with value!
						}
					});

			alert.show();
			break;
		case R.id.text_product_saleprice:
			alert.setTitle("Product Sale Price");
			alert.setMessage("Change the Product Sale Price");

			// Set an EditText view to get user input
			input = new EditText(this);
			input.setText(productDetailResourcesobj.text_product_saleprice
					.getText());
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							if (value.length() > 0) {
								productDetailResourcesobj.text_product_saleprice
										.setText(value);
								productObj.setProductSalePrice(value);
							}
							// Do something with value!
						}
					});

			alert.show();
			break;
		case R.id.text_product_name:
			alert.setTitle("Product Name");
			alert.setMessage("Change the Product Name");

			// Set an EditText view to get user input
			input = new EditText(this);
			input.setText(productDetailResourcesobj.text_product_name.getText());
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String value = input.getText().toString();
							if (value.length() > 0) {
								productDetailResourcesobj.text_product_name
										.setText(value);
								productObj.setProductName(value);
							}
							// Do something with value!
						}
					});

			alert.show();

			break;
		default:
			break;
		}// end of switch statement
	}// end of onClick

	private class AuthenticateUser extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			// loginResources.progress_login_userauthenticating;
			progressAuthenticating.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					productDetailResourcesobj.image_product_fullsize
							.setImageBitmap(bitmapImageObj);
					productDetailResourcesobj.image_product_thumbnail
							.setImageBitmap(bitmapImageObj);
				}
			});
			progressAuthenticating.cancel();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String url = params[0];

			bitmapImageObj = getBitmapFromURL(url);

			return null;
		}// do n background

	}// end of class authentication

	public Bitmap getBitmapFromURL(String src) {
		try {

			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			Log.e("Bitmap", "returned");
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Exception", e.getMessage());
			return null;
		}
	}// end of getBitmapFromURL function
}// end of activity
