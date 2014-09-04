package com.example.assignmenttest.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.assignmenttest.R;
import com.example.assignmenttest.db.AssignmentDatabase;
import com.example.assignmenttest.helper.Product;
import com.example.assignmenttest.helper.Store;
import com.example.assignmenttest.keys.KeysString;
import com.example.assignmenttest.resources.HomeResources;

public class HomeActivity extends Activity implements OnClickListener {
	ProgressDialog progressAuthenticating;
	AssignmentDatabase assignmentDatabaseObj;
	private KeysString keyStringObj;
	private JSONArray jsonArrayObj;
	String jsonString;
	Boolean isjsonDownloaded = false;
	HomeResources homeResourcesObj;
	ArrayList<Product> productsArray;
	ArrayList<String> productColors;
	ArrayList<Store> storeData;
	Intent intentObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}// end of oncreate

	private void init() {
		// TODO Auto-generated method stub
		keyStringObj = new KeysString();
		homeResourcesObj = new HomeResources(this);
		assignmentDatabaseObj = new AssignmentDatabase(this, keyStringObj);
		progressAuthenticating = new ProgressDialog(this);
		progressAuthenticating.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressAuthenticating.setIndeterminate(true);
		progressAuthenticating.setMessage("Loading please wait...");
		progressAuthenticating.setCancelable(true);
		productColors = new ArrayList<String>();
		storeData = new ArrayList<Store>();
		productsArray = new ArrayList<Product>();

	}// end of init

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_homeactivity_pcreate:

			// here work done for creating row
			productsArray.clear();
			new AuthenticateUser().execute(keyStringObj.Host_Url);

			// Toast.makeText(HomeActivity.this,
			// "Plz wait data is downloading", Toast.LENGTH_SHORT)
			// .show();

			break;

		case R.id.btn_homeactivity_pshow:
			productsArray.clear();
			productsArray = assignmentDatabaseObj.selectProductData();
			HomeResources.productsArray = productsArray;
			intentObj = new Intent(HomeActivity.this,
					CreateProductActivity.class);
			intentObj.putExtra("ActivityType", false);
			startActivity(intentObj);
			break;
		}
	}

	// *******************
	// here below all data fetching from internet is work done
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

			try {
				jsonArrayObj = new JSONArray(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			parseJsonArray();

			isjsonDownloaded = true;
			progressAuthenticating.cancel();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String urlAuthentication = params[0];

			jsonString = getJSON(urlAuthentication);
			return null;
		}// do n background

	}// end of class authentication

	public String getJSON(String address) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(address);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(HomeActivity.class.toString(), "Failed to get Connection");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public void parseJsonArray() {

		Product productobj = null;
		for (int iteration = 0; iteration < jsonArrayObj.length(); iteration++) {
			try {
				JSONObject jobj = jsonArrayObj.getJSONObject(iteration);
				productobj = new Product(jobj.getString("id"),
						jobj.getString("name"), jobj.getString("description"),
						jobj.getString("regular_price"),
						jobj.getString("sale_price"),
						jobj.getString("picture"), productColors, storeData);
				productColors.clear();
				JSONArray colorArray = jobj.getJSONArray("color");
				for (int color = 0; color < colorArray.length(); color++) {
					productColors.add((String) colorArray.get(color));
				}
				productobj.setColors(productColors);
				JSONArray storeArray = jobj.getJSONArray("store");
				storeData.clear();
				for (int storedata = 0; storedata < storeArray.length(); storedata++) {
					JSONObject storeObj = storeArray.getJSONObject(storedata);
					storeData.add(new Store(Integer.parseInt(storeObj
							.getString("id")), storeObj.getString("name")));

				}
				productobj.setStores(storeData);
				productsArray.add(productobj);
			} catch (Exception e) {
				e.printStackTrace();
			}// end of catch of exception

		}// end of for of iteration between getting all data from json
		HomeResources.productsArray = productsArray;
		intentObj = new Intent(HomeActivity.this, CreateProductActivity.class);
		intentObj.putExtra("ActivityType", true);
		startActivity(intentObj);
	}// end of parsing json

}// endof mainactivity class
