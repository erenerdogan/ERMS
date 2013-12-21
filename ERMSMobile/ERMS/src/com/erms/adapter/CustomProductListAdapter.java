package com.erms.adapter;

import java.util.ArrayList;
import java.util.List;

import com.erms.R;
import com.erms.model.ProductModel;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomProductListAdapter extends BaseAdapter {
	
	private List<ProductModel> listData;
	private LayoutInflater layoutInflater;

	

	public CustomProductListAdapter(Context context, List<ProductModel> listProduct) {
		super();
		this.listData = listProduct;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null){
			convertView = layoutInflater.inflate(R.layout.productlist_row, null);
			holder = new ViewHolder();
			holder.productImage = (ImageView) convertView.findViewById(R.id.productListImageView);
			holder.productTitle = (TextView) convertView.findViewById(R.id.productListTextView);
			holder.productPrice = (TextView) convertView.findViewById(R.id.productListTextViewPrice);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		ProductModel pm = listData.get(position);
		holder.productImage.setImageBitmap(pm.getProductImage());
		holder.productTitle.setText(pm.getProductName());
		holder.productPrice.setText(""+pm.getProductPrice()+" TL");
		return convertView;
	}
	
	static class ViewHolder{
		ImageView productImage;
		TextView productTitle;
		TextView productPrice;
	}

}
