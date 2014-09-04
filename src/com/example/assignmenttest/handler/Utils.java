package com.example.assignmenttest.handler;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;

import com.example.assignmenttest.keys.KeysString;

public class Utils {
	public static boolean isGoogleTV() {
		if (context.getPackageManager().hasSystemFeature(
				"com.google.android.tv")) {
			return true;
		}// end of if
		return false;
	}// end of google tv

	public static boolean isTablet(Context context) {

		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static Activity context;

	public static boolean isInternetConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public static boolean isAmazonDevice() {
		// TODO Auto-generated method stub
		if (Build.MANUFACTURER.equalsIgnoreCase("amazon")) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isAmazonKindeFire1stGenDevice() {
		// TODO Auto-generated method stub
		if (Build.MODEL.equalsIgnoreCase("Kindle Fire")) {
			return true;
		} else {
			return false;
		}

	}

	public static void playBeep(MediaPlayer mPlayer) {
		/*
		 * try { if (mPlayer.isPlaying()) { mPlayer.stop(); mPlayer.release();
		 * mPlayer=new MediaPlayer(); }
		 * 
		 * // AssetFileDescriptor descriptor =
		 * context.getAssets().openFd("sound/beep.wav"); mPlayer.reset();
		 * mPlayer=MediaPlayer.create(context, R.raw.beep);
		 * //mPlayer.setDataSource(descriptor.getFileDescriptor(),
		 * descriptor.getStartOffset(), descriptor.getLength()); //
		 * descriptor.close();
		 * 
		 * // mPlayer.prepare(); mPlayer.setVolume(0.5f, 0.5f);
		 * mPlayer.setLooping(false); mPlayer.start(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	public static void openLinkOnBrowser(String link) {
		if (!URLUtil.isValidUrl(link)) {
			// Toast.makeText(context, "Invalid URL specified",
			// Toast.LENGTH_SHORT).show();

		} else {
			Uri uriUrl = Uri.parse(link);
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			context.startActivity(launchBrowser);
		}
	}

	public static void hideKeyboardFromActivity(Activity context) {
		// TODO Auto-generated method stub
		InputMethodManager im = (InputMethodManager) context
				.getApplicationContext().getSystemService(
						context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(context.getWindow().getDecorView()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static void hideSoftKeyboard(Activity activity, View view) {
		InputMethodManager imm = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}

	public static void openNetworkSetting() {
		context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
	}

	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	public static boolean isAmazonTV() {
		// TODO Auto-generated method stub
		if (Build.MANUFACTURER.equalsIgnoreCase("amazon")
				&& Build.MODEL.contains("AF")) {
			return true;
		} else {
			return false;
		}

	}

	public static void rotateAnimation(View img_over_drum, float angle) {
		// TODO Auto-generated method stub

		RotateAnimation animation = new RotateAnimation(angle, angle + 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		angle = (angle + 0.5f) % 360;

		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(1000);
		animation.setFillEnabled(true);
		animation.setFillAfter(true);

		img_over_drum.startAnimation(animation);

	}

	// public static CharSequence readCredits() {
	// BufferedReader in = null;
	// try {
	// in = new BufferedReader(new InputStreamReader(context.getAssets()
	// .open(new KeysString().ASSET_CREDITS)));
	// String line;
	// StringBuilder buffer = new StringBuilder();
	// while ((line = in.readLine()) != null)
	// buffer.append(line).append('\n');
	// return buffer;
	// } catch (IOException e) {
	// return "";
	// } finally {
	// closeStream(in);
	// }
	// }
	private static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}

	public static boolean isEmailValid(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static String getWifiMacAddress() {
		WifiManager wimanager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String macAddress = wimanager.getConnectionInfo().getMacAddress();
		if (macAddress == null) {
			macAddress = "Device don't have mac address or wi-fi is disabled";
		}
		return macAddress;
	}

	public static String getDeviceUniqueID()

	{
		String UniqueID = "";

		ConnectivityManager mConnectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// Skip if no connection, or background data disabled
		TelephonyManager mTelephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();

		// Only update if WiFi or 3G is connected and not roaming
		int netType = info.getType();
		int netSubtype = info.getSubtype();
		if (UniqueID.contentEquals("")) {
			if (netType == ConnectivityManager.TYPE_WIFI) {
				// Toast.makeText(context, "WIFI", Toast.LENGTH_LONG).show();

				UniqueID = getWifiMacAddress();
			}
		}
		/*
		 * if(UniqueID.contentEquals("")) { if (netType ==
		 * ConnectivityManager.TYPE_MOBILE && netSubtype ==
		 * TelephonyManager.NETWORK_TYPE_UMTS && !mTelephony.isNetworkRoaming())
		 * { //Toast.makeText(context, "MOBILE", Toast.LENGTH_LONG).show();
		 * UniqueID =mTelephony.getDeviceId();
		 * 
		 * } } if(UniqueID.contentEquals("")) { if(mBluetoothAdapter!=null ) {
		 * //Toast.makeText(context, "BLUETOOTH", Toast.LENGTH_LONG).show();
		 * UniqueID= mBluetoothAdapter.getAddress(); } }
		 */
		if (UniqueID.contentEquals("")) {

			// Toast.makeText(context, "ANDROID ID", Toast.LENGTH_LONG).show();
			UniqueID = Settings.Secure.getString(context.getContentResolver(),
					Settings.Secure.ANDROID_ID);

		}

		// IMSI // MAC Address // Unique AndroidID
		return UniqueID;
	}

	// public static CharSequence readPrivacyPolicy() {
	// // TODO Auto-generated method stub
	// BufferedReader in = null;
	// try {
	// in = new BufferedReader(new InputStreamReader(context.getAssets()
	// .open(new KeysString().ASSET_PRIVACY_POLICY)));
	// String line;
	// StringBuilder buffer = new StringBuilder();
	// while ((line = in.readLine()) != null)
	// buffer.append(line).append('\n');
	// return buffer;
	// } catch (IOException e) {
	// return "";
	// } finally {
	// closeStream(in);
	// }
	// }
	//
	// public static CharSequence readTermOfUse() {
	// // TODO Auto-generated method stub
	// BufferedReader in = null;
	// try {
	// in = new BufferedReader(new InputStreamReader(context.getAssets()
	// .open(new KeysString().ASSET_TERMS_OF_USE)));
	// String line;
	// StringBuilder buffer = new StringBuilder();
	// while ((line = in.readLine()) != null)
	// buffer.append(line).append('\n');
	// return buffer;
	// } catch (IOException e) {
	// return "";
	// } finally {
	// closeStream(in);
	// }
	// }
	static boolean check = false;/*
								 * public static boolean
								 * isKeyBoardVisible(Activity context) { // TODO
								 * Auto-generated method stub
								 * 
								 * final View activityRootView =
								 * context.findViewById(R.i);
								 * activityRootView.getViewTreeObserver
								 * ().addOnGlobalLayoutListener(new
								 * OnGlobalLayoutListener() {
								 * 
								 * @Override public void onGlobalLayout() { //
								 * calculate the difference between the root
								 * view height and the window view height int
								 * heightDiff =
								 * activityRootView.getRootView().getHeight() -
								 * activityRootView.getHeight(); // if more than
								 * 100 pixels, its probably a keyboard... if
								 * (heightDiff > 100) { // keyboard is visible,
								 * do something here check=true; } else { //
								 * keyboard is not visible, do something here
								 * check=false; } } }); return check; }
								 */

	public static boolean isMyServiceRunning(String checkService) {
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager
				.getRunningServices(Integer.MAX_VALUE)) {
			if (checkService.equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
