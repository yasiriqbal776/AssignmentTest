package com.example.assignmenttest.helper;

import java.util.ArrayList;

public class Product {
	String productId, productName, productDescription, productRegularPrice,
			productSalePrice, productPhotoUrl;

	ArrayList<String> productColors;
	ArrayList<Store> stores;

	public Product(String productId, String productName,
			String productDescription, String productRegularPrice,
			String productSalePrice, String productPhotoUrl,
			ArrayList<String> productColors, ArrayList<Store> stores) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productRegularPrice = productRegularPrice;
		this.productSalePrice = productSalePrice;
		this.productPhotoUrl = productPhotoUrl;
		this.productColors = productColors;
		this.stores = stores;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductRegularPrice() {
		return productRegularPrice;
	}

	public void setProductRegularPrice(String productRegularPrice) {
		this.productRegularPrice = productRegularPrice;
	}

	public String getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(String productSalePrice) {
		this.productSalePrice = productSalePrice;
	}

	public String getProductPhotoUrl() {
		return productPhotoUrl;
	}

	public void setProductPhotoUrl(String productPhotoUrl) {
		this.productPhotoUrl = productPhotoUrl;
	}

	public ArrayList<String> getColors() {
		return productColors;
	}

	public void setColors(ArrayList<String> colors) {
		this.productColors = colors;
	}

	public ArrayList<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

}// end of Product Class
