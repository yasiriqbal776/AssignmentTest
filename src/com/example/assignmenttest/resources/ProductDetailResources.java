package com.example.assignmenttest.resources;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmenttest.R;
import com.example.assignmenttest.handler.PixelResolverHander;
import com.example.assignmenttest.helper.Font;
import com.example.assignmenttest.helper.Product;
import com.nextin.pixelsresolver.CustomViewHandler;

public class ProductDetailResources {
	Activity context;
	public View container_product_data;
	public ImageView image_product_thumbnail, image_product_fullsize;
	public TextView text_product_id, text_product_name, text_product_saleprice,
			text_product_regularprice, text_product_description,
			text_product_stores, text_product_colours;
	CustomViewHandler image_product_thumbnail_handler,
			image_product_fullsize_handler, text_product_id_handelr,
			text_product_name_handler, text_product_saleprice_handler,
			text_product_regularprice_handler,
			text_product_description_handler, text_product_stores_handler,
			text_product_colours_handler;
	Product productObj;
	public Button btn_productpage_update, btn_productpage_delete;

	public ProductDetailResources(Activity context, Product productObj) {
		super();
		this.context = context;
		this.productObj = productObj;
		initResources(); // all resources of HomeActivity is initialized here
		initCustomViewHandler();
		generateResourcesWithCustomHandler();
		addViewListner();
		addTextViewTypeFaces();
		addTextViewTextSize();
		addTextViewText();
	}

	private void addTextViewText() {
		// TODO Auto-generated method stub
		text_product_id.setText(productObj.getProductId());
		text_product_description.setText(productObj.getProductDescription());
		text_product_name.setText(productObj.getProductName());
		text_product_regularprice.setText(productObj.getProductRegularPrice());
		text_product_saleprice.setText(productObj.getProductSalePrice());

		text_product_stores.setText(productObj.getStores().toString());
		text_product_colours.setText(productObj.getColors().toString());

	}

	private void addTextViewTextSize() {
		// TODO Auto-generated method stub
		text_product_name_handler.textview_text_size_1(36);
		text_product_saleprice_handler.textview_text_size_1(36);
		text_product_regularprice_handler.textview_text_size_1(36);
		text_product_description_handler.textview_text_size_1(36);
		text_product_stores_handler.textview_text_size_1(36);
		text_product_colours_handler.textview_text_size_1(36);
		text_product_id_handelr.textview_text_size_1(36);

	}

	private void addTextViewTypeFaces() {
		// TODO Auto-generated method stub
		text_product_id.setTypeface(Font.getBankGthHDTypeFace());
		text_product_name.setTypeface(Font.getBankGthHDTypeFace());
		text_product_saleprice.setTypeface(Font.getBankGthHDTypeFace());
		text_product_regularprice.setTypeface(Font.getBankGthHDTypeFace());
		text_product_description.setTypeface(Font.getBankGthHDTypeFace());
		text_product_stores.setTypeface(Font.getBankGthHDTypeFace());
		text_product_colours.setTypeface(Font.getBankGthHDTypeFace());
	}

	private void generateResourcesWithCustomHandler() {
		// TODO Auto-generated method stub
		image_product_thumbnail_handler.width(300);
		image_product_thumbnail_handler.height(300);
	}

	private void initCustomViewHandler() {
		// TODO Auto-generated method stub
		image_product_thumbnail_handler = new CustomViewHandler(
				image_product_thumbnail,
				PixelResolverHander.getPixelResolverInstance());
		image_product_fullsize_handler = new CustomViewHandler(
				image_product_fullsize,
				PixelResolverHander.getPixelResolverInstance());
		text_product_id_handelr = new CustomViewHandler(text_product_id,
				PixelResolverHander.getPixelResolverInstance());
		text_product_name_handler = new CustomViewHandler(text_product_name,
				PixelResolverHander.getPixelResolverInstance());
		text_product_saleprice_handler = new CustomViewHandler(
				text_product_saleprice,
				PixelResolverHander.getPixelResolverInstance());
		text_product_regularprice_handler = new CustomViewHandler(
				text_product_regularprice,
				PixelResolverHander.getPixelResolverInstance());
		text_product_description_handler = new CustomViewHandler(
				text_product_description,
				PixelResolverHander.getPixelResolverInstance());
		text_product_colours_handler = new CustomViewHandler(
				text_product_colours,
				PixelResolverHander.getPixelResolverInstance());
		text_product_stores_handler = new CustomViewHandler(
				text_product_stores,
				PixelResolverHander.getPixelResolverInstance());

	}// end of customView handler

	private void addViewListner() {
		image_product_thumbnail.setOnClickListener((OnClickListener) context);
		text_product_id.setOnClickListener((OnClickListener) context);
		text_product_saleprice.setOnClickListener((OnClickListener) context);
		text_product_regularprice.setOnClickListener((OnClickListener) context);
		text_product_description.setOnClickListener((OnClickListener) context);
		text_product_name.setOnClickListener((OnClickListener) context);
		text_product_stores.setOnClickListener((OnClickListener) context);
		text_product_colours.setOnClickListener((OnClickListener) context);
		btn_productpage_update.setOnClickListener((OnClickListener) context);
		btn_productpage_delete.setOnClickListener((OnClickListener) context);
	}

	private void initResources() {
		image_product_thumbnail = (ImageView) context
				.findViewById(R.id.image_product);
		text_product_id = (TextView) context.findViewById(R.id.text_product_id);
		text_product_saleprice = (TextView) context
				.findViewById(R.id.text_product_saleprice);
		text_product_regularprice = (TextView) context
				.findViewById(R.id.text_product_regularprice);
		text_product_description = (TextView) context
				.findViewById(R.id.text_product_description);

		text_product_name = (TextView) context
				.findViewById(R.id.text_product_name);
		text_product_stores = (TextView) context
				.findViewById(R.id.text_product_stores);

		text_product_colours = (TextView) context
				.findViewById(R.id.text_product_colours);
		image_product_fullsize = (ImageView) context
				.findViewById(R.id.image_product_fullsize);

		container_product_data = (View) context
				.findViewById(R.id.container_product_data);

		btn_productpage_update = (Button) context
				.findViewById(R.id.btn_productpage_update);
		btn_productpage_delete = (Button) context
				.findViewById(R.id.btn_productpage_delete);

	}
}
