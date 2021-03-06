package com.erms;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.erms.model.FileProcess;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
				Intent intent = new Intent(MainActivity.this,
						CategoryActivity.class);
				startActivity(intent);

			}
		});
		orderMenu = (Button) findViewById(R.id.orderbtn);
		orderMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						OrderListActivity.class);
				startActivity(intent);
			}
		});

		tryMenu = (Button) findViewById(R.id.trybtn);
		tryMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						TryRecommendationActivity.class);
				startActivity(intent);
			}
		});

		statusMenu = (Button) findViewById(R.id.closurebtn);
		statusMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClosureSend cs = new ClosureSend();
				cs.execute();
			}
		});
	}

	private class ClosureSend extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			String url = getResources().getString(R.string.WEBIndex);
			String path = getResources().getString(R.string.WEBPath);
			String wsClosure = getResources().getString(R.string.WEBServiceOrderStatus);
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url + path + wsClosure);
			HttpResponse response;
			try {
				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();
			} catch (Exception e) {
			}

			return null;
		}
		
		@Override
        protected void onPostExecute(Void result) {
        	
            Toast.makeText(getApplicationContext(), "Your table has been successfully closure",
					   Toast.LENGTH_LONG).show();
        }
	}
}
