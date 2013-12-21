package com.erms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.erms.adapter.CustomOrderListAdapter;
import com.erms.adapter.CustomProductListAdapter;
import com.erms.model.FileProcess;
import com.erms.model.OrderModel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OrderListActivity extends Activity {

	private List<OrderModel> list;
	private Button order;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);

		Log.v("Order List", "Geldi");
		list = new ArrayList<OrderModel>();
		FileProcess fp;
		try {
			fp = new FileProcess("orderList.data");

			if (fp.dosyaVarMi()) {
				Log.v("Dosya Varmi", "Yok");
				if (fp.fileSize() != 0) {
					list = fp.fileReadOrderList();
				}
			} else {
				fp.dosyaYarat();
				Log.v("Dosya Varmi", "Var");
			}
			Log.v("List Size", "" + list.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listView = (ListView) findViewById(R.id.orderlistView);
		listView.setAdapter(new CustomOrderListAdapter(this, list));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				// TODO Auto-generated method stub
				list.remove(i);
				FileProcess fp;
				try {
					fp = new FileProcess("orderList.data");

					fp.dosyaYarat();
					fp.fileWriteOrderList(list);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Your order has been successfully deleted",
						   Toast.LENGTH_LONG).show();
				listView.invalidateViews();
			}
		});
		
		order = (Button) findViewById(R.id.orderSend);
		order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Your order will be sent within 3 minutes.",
						   Toast.LENGTH_LONG).show();
				
				OrderSend os = new OrderSend();
				os.execute();
				
			}
		});
	}

	private class OrderSend extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
        	
            try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        	list.remove(0);
			FileProcess fp;
			try {
				fp = new FileProcess("orderList.data");

				fp.dosyaYarat();
				fp.fileWriteOrderList(list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            listView.invalidateViews();
            Toast.makeText(getApplicationContext(), "Your order has been successfully sent",
					   Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
        	Toast.makeText(getApplicationContext(), "Your order will be sent within 3 minutes.",
					   Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
