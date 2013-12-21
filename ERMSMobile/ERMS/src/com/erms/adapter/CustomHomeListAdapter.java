package com.erms.adapter;

import java.util.ArrayList;
import java.util.List;

import com.erms.R;
import com.erms.model.CategoryModel;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomHomeListAdapter extends BaseAdapter {
	
	private List<CategoryModel> listData;
	private LayoutInflater layoutInflater;

	

	public CustomHomeListAdapter(Context context, List<CategoryModel> listData) {
		super();
		this.listData = listData;
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
			convertView = layoutInflater.inflate(R.layout.homelist_row, null);
			holder = new ViewHolder();
			holder.homeMenuIcon = (ImageView) convertView.findViewById(R.id.homeListImageView);
			holder.homeMenuTitle = (TextView) convertView.findViewById(R.id.homeListTextView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		CategoryModel cm = listData.get(position);
		holder.homeMenuIcon.setImageBitmap(cm.getCategoryImage());
		holder.homeMenuTitle.setText(cm.getCategoryName());
		
		return convertView;
	}
	
	static class ViewHolder{
		ImageView homeMenuIcon;
		TextView homeMenuTitle;
	}

}