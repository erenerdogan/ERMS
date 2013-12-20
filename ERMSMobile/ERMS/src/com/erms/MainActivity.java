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

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			List<CategoryModel> arrayList = new ArrayList<CategoryModel>();

			String url = "http://localhost:8084/ERMS/webservice.xhtml";
			
			JSONArray json = null;
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				HttpResponse response;
				try {
					response = httpClient.execute(httpget);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						InputStream instream = entity.getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(instream));
						StringBuilder sb = new StringBuilder();
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
						String data = sb.toString();
						Log.i("Data", data);
						TextView tw = 
						json = new JSONArray(data);
					}
				} catch (Exception e) {
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*CategoryModel cm = new CategoryModel();
			arrayList = cm.parseJSON(jsonArray);
			
			for (CategoryModel categoryModel : arrayList) {
				Log.w("CategoryID", ""+categoryModel.getCategoryID());
				Log.w("CategoryName", categoryModel.getCategoryName());
				Log.w("CategoryPath", categoryModel.getCategoryImagePath());
				
			}
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class FetchJSONModel extends AsyncTask<String, Void, JSONArray> {

		@Override
		protected JSONArray doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONArray json = null;
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
						StringBuilder sb = new StringBuilder();
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
						String data = sb.toString();
						json = new JSONArray(data);
					}
				} catch (Exception e) {
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}

	}
}