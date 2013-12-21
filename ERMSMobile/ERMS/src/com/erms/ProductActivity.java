package com.erms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.erms.MainActivity.FetchJSONModel;
import com.erms.MainActivity.ImageDownloadModel;
import com.erms.adapter.CustomHomeListAdapter;
import com.erms.adapter.CustomProductListAdapter;
import com.erms.model.ProductModel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class ProductActivity extends Activity {
	private List<ProductModel> listProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		
		int categoryID = getIntent().getExtras().getInt("categoryID");
		Log.w("CategoryID", ""+categoryID);
		
		String url = getResources().getString(R.string.WEBIndex);
		String path = getResources().getString(R.string.WEBPath);
		String image = getResources().getString(R.string.WEBImage);
		String appkey = getResources().getString(R.string.APPKEY);
		String webserviceProduct = getResources().getString(R.string.WEBServiceProduct);
		
		
		listProduct = new ArrayList<ProductModel>();
		FetchJSONModel fetchJSONModel2 = new FetchJSONModel();
		try {
			JSONArray jsonArray = fetchJSONModel2.execute(url+path+webserviceProduct+appkey).get();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				ProductModel pm = new ProductModel();
				pm.setProductID(json.getInt("productID"));
				pm.setProductName(json.getString("productName"));
				pm.setProductPrice(json.getInt("productPrice"));
				
				JSONObject json2 =  json.getJSONObject(("productImageModel"));
				ImageDownloadModel idm = new ImageDownloadModel();
				pm.setProductImage(idm.execute(url+path+image+json2.getString("imagePath")).get());
				
				JSONObject json3 =  json.getJSONObject(("productCategoryModel"));
				pm.setCategoryID(json3.getInt("categoryID"));
				
				
				
				listProduct.add(pm);
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<ProductModel> pmList = new ArrayList<ProductModel>();
		
		for (Iterator iterator = listProduct.iterator(); iterator.hasNext();) {
			ProductModel type = (ProductModel) iterator.next();
			if(type.getCategoryID() == categoryID){
				pmList.add(type);
			}
		}
		
		final ListView listView = (ListView) findViewById(R.id.productlistView);
		listView.setAdapter(new CustomProductListAdapter(this, pmList));
	}

	public class FetchJSONModel extends AsyncTask<String, Void, JSONArray> {

		@Override
		protected JSONArray doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONArray json = null;
			StringBuilder sb = new StringBuilder();
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(params[0]);
				HttpResponse response;
				try {
					response = httpClient.execute(httpget);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream instream = entity.getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(instream));

						String line = null;
						try {
							while ((line = reader.readLine()) != null) {
								sb.append(line + "\n");
							}
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								instream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					}
				} catch (Exception e) {
				}

				String data = sb.toString();
				data = data.trim();

				json = new JSONArray(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}

	}
	
	public class ImageDownloadModel extends AsyncTask<String, Void, Bitmap> {
		
		@Override
		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
				in.close();
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

	}
}
