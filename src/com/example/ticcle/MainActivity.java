package com.example.ticcle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnKeyListener {
	private AlertDialog mWaitingDialog;
	ListView list;
	ImageLoadAdapter adapter;
	private int cnt = 0;
	private String[] mStrings = {
			"http://blogfiles.naver.net/20150205_133/npcs4511_1423098835709mrDnr_JPEG/yjbrXGzBpSBkKQ6Dmvi9_99_20150205083506.jpg",
			"http://image.lotte.com/goods/49/91/79/50/2/250799149_1_280.jpg",
			"http://postfiles14.naver.net/20160531_77/cocacola1585_1464703604899fkAL6_JPEG/123.jpg",
			"http://postfiles3.naver.net/20160531_162/cocacola1585_1464703552297PfsTk_PNG/%BE%C6%C0%CC%C6%F96%C7%C3%B7%AF%BD%BA_%BB%E7%C1%F81.png",
			"http://blogfiles.naver.net/20150205_133/npcs4511_1423098835709mrDnr_JPEG/yjbrXGzBpSBkKQ6Dmvi9_99_20150205083506.jpg",
			"http://image.lotte.com/goods/49/91/79/50/2/250799149_1_280.jpg",
			"http://postfiles14.naver.net/20160531_77/cocacola1585_1464703604899fkAL6_JPEG/123.jpg",
			"http://postfiles3.naver.net/20160531_162/cocacola1585_1464703552297PfsTk_PNG/%BE%C6%C0%CC%C6%F96%C7%C3%B7%AF%BD%BA_%BB%E7%C1%F81.png",
			"http://blogfiles.naver.net/20150205_133/npcs4511_1423098835709mrDnr_JPEG/yjbrXGzBpSBkKQ6Dmvi9_99_20150205083506.jpg",
			"http://image.lotte.com/goods/49/91/79/50/2/250799149_1_280.jpg",
			"http://postfiles14.naver.net/20160531_77/cocacola1585_1464703604899fkAL6_JPEG/123.jpg"
			

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView) findViewById(R.id.mainlist);

		// Create custom adapter for listview
		adapter = new ImageLoadAdapter(this, mStrings);

		// Set adapter to listview
		list.setAdapter(adapter);
		list.setOnKeyListener(this);

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cnt = 0;
	}

	public void onItemClick(int mPosition) {
		// String tempValues = mStrings[mPosition];
		//
		// Toast.makeText(MainActivity.this,
		// "Image URL : "+tempValues,
		// Toast.LENGTH_LONG).show();
		Intent i = new Intent(MainActivity.this, DetailActivity.class);
		i.putExtra("item", mPosition);

		startActivity(i);
	}

	@Override
	public void onDestroy() {
		// Remove adapter refference from list
		list.setAdapter(null);
		super.onDestroy();
	}

	class makeDataJob extends AsyncTask<Void, Void, Integer> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			showWaitingDialog("������Ʈ", "update");

		}

		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// if (mContext != null && mCallback != null) {
			//
			// if (result != null) {
			// mCallback.onCompleted(mContext, result);
			// }
			// }
			//
			// if (mParentActivity != null) {
			dismissWaitingDialog();
			// }
		}

	}

	private void dismissWaitingDialog() {
		Log.i("", "[Test] dismissWaitingDialog!! ");
		try {

			mWaitingDialog.dismiss();
			mWaitingDialog = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showWaitingDialog(String title, String message) {
		Log.i("", "[Test] showWaitingDialog!! ");
		try {
			ProgressDialog dlgProgress = new ProgressDialog(this);
			// dlgProgress.setTitle(title);
			dlgProgress.setMessage(message);
			dlgProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dlgProgress.setCancelable(false);
			mWaitingDialog = dlgProgress;
			mWaitingDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("", "[RemoveAccountTask] activity terminated");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(MainActivity.this, Mypage.class);
			

			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.e("ticcle", "cnt = "+cnt);
		if (keyCode == KeyEvent.KEYCODE_BACK && cnt == 0) {
			Log.e("ticcle", "cnt = 0");
			 Toast.makeText(MainActivity.this,
			 "�ѹ��� ������ ���� �˴ϴ�",
			 Toast.LENGTH_SHORT).show();
			cnt++;
			
			return true;
		} 
//		else if(keyCode == KeyEvent.KEYCODE_BACK && cnt == 1) {
//			Log.e("ticcle", "cnt = 1");
////			 ActivityManager am = (ActivityManager)getSystemService("SYSTEM_ACTIVITY");
////			 am.killBackgroundProcesses(getPackageName());
//			  android.os.Process.killProcess(android.os.Process.myPid());
//			cnt = 0;
//			return true;
//		}
//		  android.os.Process.killProcess(android.os.Process.myPid());
		
		return false;
	}

}