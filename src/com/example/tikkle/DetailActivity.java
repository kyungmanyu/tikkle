package com.example.tikkle;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class DetailActivity extends Activity {
	private WebView mWebview;
	private Button purchase;
	private ProgressDialog progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(getWindow().FEATURE_PROGRESS);
		setContentView(R.layout.activity_detail);

		// http://www.apple.com/kr/macbook-pro/
		int item = getIntent().getIntExtra("item", 0);
		String addr = null;
		if (item == 0) {
			addr = "http://www.apple.com/kr/macbook-pro";
		} else {
			addr = "http://img.player.co.kr/prd_img/2015/07/15/317821021/001.jpg";
		}
		// String addr = "http://www.apple.com/kr/macbook-pro";
		// String addr =
		// "http://img.player.co.kr/prd_img/2015/07/15/317821021/001.jpg";

		purchase = (Button) findViewById(R.id.purchase);
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

		progressBar = ProgressDialog.show(DetailActivity.this, "", "Loading...");
		mWebview = (WebView) findViewById(R.id.webview);
		mWebview.setInitialScale(1);
		mWebview.getSettings().setJavaScriptEnabled(true);
		mWebview.getSettings().setLoadWithOverviewMode(true);
		mWebview.getSettings().setUseWideViewPort(true);
		mWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebview.setScrollbarFadingEnabled(false);

		mWebview.setWebViewClient(new WebViewClient() {

			public void onProgressChanged(WebView view, int progress) {
				// Activities and WebViews measure progress with different
				// scales.
				// The progress meter will automatically disappear when we reach
				// 100%
				DetailActivity.this.setProgress(progress * 1000);
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);
				return true;
			}

			public void onPageFinished(WebView view, String url) {

				if (progressBar.isShowing()) {
					progressBar.dismiss();
				}
			}

			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(DetailActivity.this, "error " + description, Toast.LENGTH_SHORT).show();
			}
		});

		mWebview.loadUrl(addr);
		mWebview.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
					mWebview.goBack();
					return true;
				}
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maillist, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.add) {
			Toast toast = null;
			toast = Toast.makeText(this, "응모가 완료 되었습니다", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 50);
			toast.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
