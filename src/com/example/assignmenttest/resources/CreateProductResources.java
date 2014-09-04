package com.example.assignmenttest.resources;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.assignmenttest.R;
import com.example.assignmenttest.handler.PixelResolverHander;
import com.nextin.pixelsresolver.CustomViewHandler;

public class CreateProductResources {
	public ListView list_products;
	public Button btn_homePageRevert;

	CustomViewHandler list_products_handler, btn_homePageRevert_handler;
	Activity context;

	public CreateProductResources(Activity context) {
		this.context = context;
		initResources();
		initCustomViewHandler();
		generateResourcesWithHandler();
		addViewListner();
	}// end of constructor

	private void addViewListner() {
		// TODO Auto-generated method stub
		btn_homePageRevert.setOnClickListener((OnClickListener) context);
	}

	private void generateResourcesWithHandler() {
		// TODO Auto-generated method stub
		list_products_handler.marginBottom(300);
		btn_homePageRevert_handler.height(140);
		btn_homePageRevert_handler.width(230);
		btn_homePageRevert_handler.marginBottom(40);
	}

	private void initCustomViewHandler() {
		// TODO Auto-generated method stub
		list_products_handler = new CustomViewHandler(list_products,
				PixelResolverHander.getPixelResolverInstance());
		btn_homePageRevert_handler = new CustomViewHandler(btn_homePageRevert,
				PixelResolverHander.getPixelResolverInstance());

	}

	private void initResources() {
		// TODO Auto-generated method stub
		list_products = (ListView) context
				.findViewById(R.id.listview_showProduct);
		btn_homePageRevert = (Button) context
				.findViewById(R.id.btn_homePageRevert);
	}
}// end of class resources
