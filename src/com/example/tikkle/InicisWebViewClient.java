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
				intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //IntentURIμ²λ¦¬
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
	 * @return ?΄?Ή scheme? ???΄ μ²λ¦¬λ₯? μ§μ  ??μ§? ?¬λΆ?
	 * 
	 * κ²°μ λ₯? ?? 3rd-party ?±?΄ ?μ§? ?€μΉλ?΄?μ§? ?? ActivityNotFoundException?΄ λ°μ?? κ²½μ° μ²λ¦¬?©??€.
	 * ?¬κΈ°μ handler?μ§???? scheme? ???΄?? intentλ‘λ??° Package? λ³? μΆμΆ?΄ κ°??₯??€λ©? ?€??? packageName?Όλ‘? market?΄??©??€.  
	 * 
	 */
	protected boolean handleNotFoundPaymentScheme(String scheme) {
		//PG?¬?? ?ΈμΆν? url? package? λ³΄κ? ??΄ ActivityNotFoundException?΄ ? ? market ?€??΄ ??? κ²½μ°
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
