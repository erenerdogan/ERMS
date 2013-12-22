package com.erms;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.erms.model.FileProcess;
import com.erms.model.OrderModel;
import com.erms.model.ProductModel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends Activity {

	private ImageView pdImage;
	private TextView pdDescription, pdPrice, pdCalorie;
	private EditText pdNumber;
	private Button pdAddOrder;
	private ProductModel pm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detail);

		Intent intent = getIntent();

		Bundle bundle = intent.getExtras();

		pm = (ProductModel) bundle.getSerializable("product");

		pdImage = (ImageView) findViewById(R.id.pdetailimageView);
		pdImage.setImageBitmap(pm.getProductImage());
		pdDescription = (TextView) findViewById(R.id.pdetailtextView);
		pdDescription.setText(pm.getProductDescription());
		pdPrice = (TextView) findViewById(R.id.pdetailpricetextView);
		pdPrice.setText("" + pm.getProductPrice() + " TL");
		pdCalorie = (TextView) findViewById(R.id.pdetailcaloritextView);
		pdCalorie.setText("" + pm.getProductCalorie() + " Cal");

		pdNumber = (EditText) findViewById(R.id.pdetailnumberEditText);
		pdNumber.setText("1");
		pdAddOrder = (Button) findViewById(R.id.pdetailaddbtn);

		pdAddOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				OrderModel om = new OrderModel();
				om.setOrderNum(Integer.parseInt("" + pdNumber.getText()));
				om.setOrderProductName(pm.getProductName());
				om.setOrderProductCalorie(pm.getProductCalorie());
				om.setOrderProductPrice(pm.getProductPrice());
				
				FileProcess fp;
				try {
					fp = new FileProcess("orderList.data");
					List<OrderModel> list = new ArrayList<OrderModel>();
					if (fp.dosyaVarMi()) {
						Log.v("Dosya Varmi", "Yok");
						if (fp.fileSize() != 0) {
							list = fp.fileReadOrderList();
						}
					} else {
						fp.dosyaYarat();
						Log.v("Dosya Varmi", "Var");
					}
					Log.v("Gel", "Gel");
					list.add(om);
					fp.fileWriteOrderList(list);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Your order has been successfully added",
						   Toast.LENGTH_LONG).show();
			}
		});
	}

}
