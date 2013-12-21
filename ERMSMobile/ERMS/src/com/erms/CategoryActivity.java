package com.erms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.erms.adapter.CustomHomeListAdapter;
import com.erms.model.CategoryModel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CategoryActivity extends Activity {

	private List<CategoryModel> listCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		String url = getResources().getString(R.string.WEBIndex);
		String path = getResources().getString(R.string.WEBPath);
		String webserviceCategory = getResources().getString(R.string.WEBServiceCategory);
		String image = getResources().getString(R.string.WEBImage);
		String appkey = getResources().getString(R.string.APPKEY);
		
		listCategory = new ArrayList<CategoryModel>();
		FetchJSONModel fetchJSONModel = new FetchJSONModel();
		try {
			JSONArray jsonArray = fetchJSONModel.execute(url+path+webserviceCategory+appkey).get();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				CategoryModel cm = new CategoryModel();
				cm.setCategoryID(json.getInt("categoryID"));
				cm.setCategoryName(json.getString("categoryName"));
				JSONObject json2 =  json.getJSONObject(("categoryImageModel"));
				ImageDownloadModel idm = new ImageDownloadModel();
				cm.setCategoryImage(idm.execute(url+path+image+json2.getString("imagePath")).get());
				
				listCategory.add(cm);
				
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
		

		final ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new CustomHomeListAdapter(this, listCategory));
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				// TODO Auto-generated method stub
				Object o = listView.getItemAtPosition(i);
				CategoryModel cm = (CategoryModel) o;
				
				Intent intent = new Intent(CategoryActivity.this,
						ProductActivity.class);
				intent.putExtra("categoryID", cm.getCategoryID());
				startActivity(intent);
				
			}
		});
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
