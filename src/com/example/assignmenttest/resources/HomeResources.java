package com.example.assignmenttest.resources;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.assignmenttest.R;
import com.example.assignmenttest.helper.Product;

public class HomeResources {
	public static ArrayList<Product> productsArray;
	Activity context;
	Button btn_homeactivity_pcreate, btn_homeactivity_pshow;
	ListView listView_products;

	public HomeResources(Activity context) {
		super();
		this.context = context;
		initResources(); // all resources of HomeActivity is initialized here
		addViewListner();
	}

	private void addViewListner() {
		// TODO Auto-generated method stub
		btn_homeactivity_pshow.setOnClickListener((OnClickListener) context);
		btn_homeactivity_pcreate.setOnClickListener((OnClickListener) context);
	}

	private void initResources() {
		// TODO Auto-generated method stub
		btn_homeactivity_pcreate = (Button) context
				.findViewById(R.id.btn_homeactivity_pcreate);
		btn_homeactivity_pshow = (Button) context
				.findViewById(R.id.btn_homeactivity_pshow);
	}
}// end of homeresources class
