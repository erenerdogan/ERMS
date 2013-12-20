package com.erms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CategoryModel {

	private String categoryName;
	private int categoryID;
	private String categoryImagePath;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryImagePath() {
		return categoryImagePath;
	}

	public void setCategoryImagePath(String categoryImagePath) {
		this.categoryImagePath = categoryImagePath;
	}

	public List<CategoryModel> parseJSON(JSONArray jsonArray)
			throws JSONException, InterruptedException, ExecutionException,
			Exception {

		List<CategoryModel> arrayList = new ArrayList<CategoryModel>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CategoryModel category = new CategoryModel();
			JSONObject json = jsonArray.getJSONObject(i);

			category.setCategoryID(json.getInt("categoryID"));
			category.setCategoryName(json.getString("categoryName"));
			JSONObject j = json.getJSONObject("categoryImageModel");
			category.setCategoryImagePath(j.getString("imagePath"));
			arrayList.add(category);
		}
		return arrayList;
	}
}
