package com.erms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TryRecommendationActivity extends Activity {
	
	private EditText peopleText, amountText;
	private Button tryBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_try_recommendation);
		
		peopleText = (EditText) findViewById(R.id.tryPeopleEdit);
		amountText = (EditText) findViewById(R.id.tryAmountEdit);
		
		tryBtn = (Button) findViewById(R.id.trybtn);
		tryBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TryRecommendationActivity.this, ProductActivity.class);
				double total = Double.parseDouble(""+amountText.getText()) / Double.parseDouble(""+peopleText.getText());
				intent.putExtra("categoryID", 0);
				intent.putExtra("total", total);
				startActivity(intent);
			}
		});
	}

}
