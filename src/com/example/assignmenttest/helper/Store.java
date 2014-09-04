package com.example.assignmenttest.helper;

public class Store {
	int storeId;

	public Store(int storeId, String storeName) {
		this.storeId = storeId;
		this.storeName = storeName;
	}

	String storeName;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}// end of Store Class
