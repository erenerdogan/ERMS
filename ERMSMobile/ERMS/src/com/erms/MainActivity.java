package com.erms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.erms.adapter.CustomHomeListAdapter;
import com.erms.adapter.CustomProductListAdapter;
import com.erms.model.CategoryModel;
import com.erms.model.ProductModel;

public class MainActivity extends Activity {
	
	private Button categoryMenu, tryMenu, orderMenu, statusMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		categoryMenu = (Button) findViewById(R.id.menubtn);
		categoryMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
				startActivity(intent);
				
			}
		});
		orderMenu = (Button) findViewById(R.id.orderbtn);
		orderMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
				startActivity(intent);
			}
		});
		
		tryMenu = (Button) findViewById(R.id.trybtn);
		tryMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, TryRecommendationActivity.class);
				startActivity(intent);
			}
		});
	}


}
