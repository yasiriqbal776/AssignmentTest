package com.example.assignmenttest.customadopter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttest.R;
import com.example.assignmenttest.db.AssignmentDatabase;
import com.example.assignmenttest.handler.PixelResolverHander;
import com.example.assignmenttest.helper.Font;
import com.example.assignmenttest.helper.Product;
import com.example.assignmenttest.keys.KeysString;
import com.nextin.pixelsresolver.CustomViewHandler;

public class SetListAdapter extends BaseAdapter {
	private Context context;
	ArrayList<Product> productArray;
	boolean isCreatedActivity;

	public SetListAdapter(Context context, ArrayList<Product> productArray,
			boolean isCreatedActivity) {
		this.context = context;
		this.productArray = productArray;
		this.isCreatedActivity = isCreatedActivity;

	}

	@SuppressLint("InflateParams")
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View List_Products;

		if (convertView == null) {

			List_Products = new View(context);
			List_Products = inflater.inflate(R.layout.template_customlistview,
					null);
			ImageView img_customlist_urlimage;
			TextView pName, pId, pPrice;
			Button btn_create_record;
			img_customlist_urlimage = (ImageView) List_Products
					.findViewById(R.id.img_customlist_urlimage);

			CustomViewHandler img_customlist_urlimage_handler, pName_handler, pId_handler, pPrice_handler, btn_create_record_handler;
			// ************Text Set
			pName = (TextView) List_Products
					.findViewById(R.id.txt_customadopter_pname);
			pId = (TextView) List_Products
					.findViewById(R.id.txt_customadopter_pid);
			pPrice = (TextView) List_Products
					.findViewById(R.id.txt_customadopter_pprice);
			btn_create_record = (Button) List_Products
					.findViewById(R.id.btn_customadapter_createproduct);
			// *****************************************************
			img_customlist_urlimage_handler = new CustomViewHandler(
					img_customlist_urlimage,
					PixelResolverHander.getPixelResolverInstance());
			pName_handler = new CustomViewHandler(pName,
					PixelResolverHander.getPixelResolverInstance());
			pId_handler = new CustomViewHandler(pId,
					PixelResolverHander.getPixelResolverInstance());
			pPrice_handler = new CustomViewHandler(pPrice,
					PixelResolverHander.getPixelResolverInstance());
			btn_create_record_handler = new CustomViewHandler(
					btn_create_record,
					PixelResolverHander.getPixelResolverInstance());

			// *********************************************
			// creating with custom handler

			pName_handler.textview_text_size_1(36);
			pId_handler.textview_text_size_1(36);
			pPrice_handler.textview_text_size_1(36);
			img_customlist_urlimage_handler.widthAsheight(150);
			img_customlist_urlimage_handler.heightAsWidth(150);
			btn_create_record_handler.width(350);
			btn_create_record_handler.height(120);
			// ******************************************
			pName.setTypeface(Font.getBankGthHDTypeFace());
			pId.setTypeface(Font.getBankGthHDTypeFace());
			pPrice.setTypeface(Font.getBankGthHDTypeFace());

			// /***************************************
			pName.setText(productArray.get(position).getProductName());
			pId.setText(productArray.get(position).getProductId());
			pPrice.setText(productArray.get(position).getProductRegularPrice());

			if (isCreatedActivity) {
				btn_create_record.setVisibility(Button.VISIBLE);
				img_customlist_urlimage.setVisibility(ImageView.GONE);
				btn_create_record.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AssignmentDatabase assignmentDatabase = new AssignmentDatabase(
								context, new KeysString());
						if (assignmentDatabase.insertProductData(productArray
								.get(position))) {
							Toast.makeText(context, "Congrats Data Created",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									context,
									"Sorry Data not created either already created",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			} else {
				btn_create_record.setVisibility(Button.INVISIBLE);
				img_customlist_urlimage.setVisibility(ImageView.GONE);
			}
			// btn click listner

			// *************************
		} else {
			List_Products = (View) convertView;
		}

		return List_Products;
	}

	public int getCount() {
		return productArray.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

}
