package com.example.tikkle;

import java.net.URISyntaxException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InicisWebViewClient extends WebViewClient {
	
	private Activity activity;
	
	public InicisWebViewClient(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		
		if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript:")) {
			Intent intent = null;
			
			try {
				intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //IntentURI처리
				Uri uri = Uri.parse(intent.getDataString());
				
				activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
				return true;
			} catch (URISyntaxException ex) {
				return false;
			} catch (ActivityNotFoundException e) {
				if ( intent == null )	return false;
				
				if ( handleNotFoundPaymentScheme(intent.getScheme()) )	return true;
				
				String packageName = intent.getPackage();
		        if (packageName != null) {
		            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
		            return true;
		        }
		        
		        return false;
			}
		}
		
		return false;
	}
	
	/**
	 * @param scheme
	 * @return ?��?�� scheme?�� ???�� 처리�? 직접 ?��?���? ?���?
	 * 
	 * 결제�? ?��?�� 3rd-party ?��?�� ?���? ?��치되?��?���? ?��?�� ActivityNotFoundException?�� 발생?��?�� 경우 처리?��?��?��.
	 * ?��기서 handler?���??��?? scheme?�� ???��?��?�� intent로�??�� Package?���? 추출?�� �??��?��?���? ?��?��?��?�� packageName?���? market?��?��?��?��?��.  
	 * 
	 */
	protected boolean handleNotFoundPaymentScheme(String scheme) {
		//PG?��?��?�� ?��출하?�� url?�� package?��보�? ?��?�� ActivityNotFoundException?�� ?�� ?�� market ?��?��?�� ?��?��?�� 경우
		if ( PaymentScheme.ISP.equalsIgnoreCase(scheme) ) {
			activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PaymentScheme.PACKAGE_ISP)));
			return true;
		} else if ( PaymentScheme.BANKPAY.equalsIgnoreCase(scheme) ) {
			activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PaymentScheme.PACKAGE_BANKPAY)));
			return true;
		}
		
		return false;
	}
	
}
