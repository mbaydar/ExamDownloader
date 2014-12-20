package com.baydar.examdownloader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();

		final Button alesButton = (Button) findViewById(R.id.button3);
		final Button ydsButton = (Button) findViewById(R.id.button5);
		final Button osysButton = (Button) findViewById(R.id.button4);
		// final Button directoryButton = (Button) findViewById(R.id.button1);

		osysButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent nextScreen = new Intent(getApplicationContext(),
						OsysActivity.class);
				Bundle b = new Bundle();
				b.putBoolean("key", isCheckboxChecked());
				nextScreen.putExtras(b);
				startActivity(nextScreen);
			}
		});

		alesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent nextScreen = new Intent(getApplicationContext(),
						AlesActivity.class);
				Bundle b = new Bundle();
				b.putBoolean("key", isCheckboxChecked());
				nextScreen.putExtras(b);
				startActivity(nextScreen);

			}
		});

		ydsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent nextScreen = new Intent(getApplicationContext(),
						YDSActivity.class);
				Bundle b = new Bundle();
				b.putBoolean("key", isCheckboxChecked());
				nextScreen.putExtras(b);
				startActivity(nextScreen);
			}
		});
	}

	public boolean isCheckboxChecked() {
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		if (checkBox.isChecked()) {
			return true;
		} else {
			return false;
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_my, container,
					false);
			return rootView;
		}
	}

	/**
	 * This class makes the ad request and loads the ad.
	 */
	public static class AdFragment extends Fragment {
		private AdView mAdView;

		public AdFragment() {
		}

		@Override
		public void onActivityCreated(Bundle bundle) {
			super.onActivityCreated(bundle);
			// Gets the ad view defined in layout/ad_fragment.xml with ad unit
			// ID set in
			// values/strings.xml.
			mAdView = (AdView) getView().findViewById(R.id.adView);
			// Create an ad request. Check logcat output for the hashed device
			// ID to
			// get test ads on a physical device. e.g.
			// "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
			AdRequest adRequest = new AdRequest.Builder().addTestDevice(
					AdRequest.DEVICE_ID_EMULATOR).build();
			// Start loading the ad in the background.
			mAdView.loadAd(adRequest);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_ad, container, false);
		}

		/** Called when leaving the activity */
		@Override
		public void onPause() {
			if (mAdView != null) {
				mAdView.pause();
			}
			super.onPause();
		}

		/** Called when returning to the activity */
		@Override
		public void onResume() {
			super.onResume();
			if (mAdView != null) {
				mAdView.resume();
			}
		}

		/** Called before the activity is destroyed */
		@Override
		public void onDestroy() {
			if (mAdView != null) {
				mAdView.destroy();
			}
			super.onDestroy();
		}
	}

}
