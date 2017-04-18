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
				intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); //IntentURIì²˜ë¦¬
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
	 * @return ?•´?‹¹ scheme?— ???•´ ì²˜ë¦¬ë¥? ì§ì ‘ ?•˜?Š”ì§? ?—¬ë¶?
	 * 
	 * ê²°ì œë¥? ?œ„?•œ 3rd-party ?•±?´ ?•„ì§? ?„¤ì¹˜ë˜?–´?ˆì§? ?•Š?•„ ActivityNotFoundException?´ ë°œìƒ?•˜?Š” ê²½ìš° ì²˜ë¦¬?•©?‹ˆ?‹¤.
	 * ?—¬ê¸°ì„œ handler?˜ì§??•Š?? scheme?— ???•´?„œ?Š” intentë¡œë??„° Package? •ë³? ì¶”ì¶œ?´ ê°??Š¥?•˜?‹¤ë©? ?‹¤?Œ?—?„œ packageName?œ¼ë¡? market?´?™?•©?‹ˆ?‹¤.  
	 * 
	 */
	protected boolean handleNotFoundPaymentScheme(String scheme) {
		//PG?‚¬?—?„œ ?˜¸ì¶œí•˜?Š” url?— package? •ë³´ê? ?—†?–´ ActivityNotFoundException?´ ?‚œ ?›„ market ?‹¤?–‰?´ ?•ˆ?˜?Š” ê²½ìš°
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
