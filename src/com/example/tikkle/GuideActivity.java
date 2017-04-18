package com.example.tikkle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class GuideActivity extends Activity {

	Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.mainview);
//		layout.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(GuideActivity.this, MainActivity.class);
//				// i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//				// Intent.FLAG_ACTIVITY_SINGLE_TOP);
//				i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//				startActivity(i);
//			}
//		});
		startloading();
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Intent i = new Intent(GuideActivity.this, MainActivity.class);
				// TODO Auto-generated method stub
				switch (msg.what) {

				case 4:

					// i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
					// Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(i);
					break;

				default:

				}
				super.handleMessage(msg);
			}
		};

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		

		super.onResume();
	}

	private void startloading() {
		Thread loading = new Thread(new Runnable() {
			int index = 0;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (index < 4) {
					index++;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				mHandler.sendEmptyMessage(index);
			}
		});
		loading.start();

	}

}
