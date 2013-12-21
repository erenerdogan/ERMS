package com.erms.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class CategoryModel implements Serializable{

	private static final long serialVersionUID = 7688468724394897021L;

	private String categoryName;
	private int categoryID;
	private String categoryImagePath;
	private Bitmap categoryImage;
	

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

	public Bitmap getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(Bitmap categoryImage) {
		this.categoryImage = categoryImage;
	}
	
	
}
