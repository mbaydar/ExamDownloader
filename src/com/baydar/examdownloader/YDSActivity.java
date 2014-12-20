package com.baydar.examdownloader;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class YDSActivity extends Activity {

	static boolean alreadyExist = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yds);
		getActionBar().hide();

		final Button alesButton1 = (Button) findViewById(R.id.button1);
		final Button alesButton2 = (Button) findViewById(R.id.button2);
		final Button alesButton3 = (Button) findViewById(R.id.button3);
		final Button alesButton4 = (Button) findViewById(R.id.button4);
		final Button alesButton5 = (Button) findViewById(R.id.button5);
		final Button alesButton6 = (Button) findViewById(R.id.button6);
		final Button alesButton7 = (Button) findViewById(R.id.button7);
		final Button alesButton8 = (Button) findViewById(R.id.button8);
		final Button alesButton9 = (Button) findViewById(R.id.button9);
		final Button alesButton10 = (Button) findViewById(R.id.button10);

		alesButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("yds2013-sonbahar.pdf").execute(
						"http://www.osym.gov.tr/dosya/1-69894/h/ingilizce.pdf",
						"yds2013-sonbahar.pdf");
			}
		});
		alesButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("yds2013-ilkbahar.pdf").execute(
						"http://www.osym.gov.tr/dosya/1-69059/h/ingilizce.pdf",
						"yds2013-ilkbahar.pdf");
			}
		});

		alesButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2012-sonbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-61089/h/kpdsingilizce2012sonbahar2.pdf",
								"kpds2012-sonbahar.pdf");
			}
		});

		alesButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2012-ilkbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-60075/h/kpds2012010109kamuskmaster1-ingilizce.pdf",
								"kpds2012-ilkbahar.pdf");
			}
		});

		alesButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2011-sonbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-58792/h/kpds20110201ingilizcemaster.pdf",
								"kpds2011-sonbahar.pdf");
			}
		});

		alesButton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2011-ilkbahar.pdf").execute(
						"http://www.osym.gov.tr/dosya/1-57694/h/ingilizce.pdf",
						"kpds2011-ilkbahar.pdf");
			}
		});

		alesButton7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2010-sonbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-56758/h/2010kpdssonbaharingilizce.pdf",
								"kpds2010-sonbahar.pdf");
			}
		});

		alesButton8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2010-ilkbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-52736/h/kpds2010ilkbaharingilizce.pdf",
								"kpds2010-ilkbahar.pdf");
			}
		});

		alesButton9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2009-sonbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-51563/h/kpds2009sonbaharingilizce.pdf",
								"kpds2009-sonbahar.pdf");
			}
		});

		alesButton10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("kpds2009-ilkbahar.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-49875/h/kpds2009ilkbaharingilizce.pdf",
								"kpds2009-ilkbahar.pdf");
			}
		});

	}

	public boolean isCheckboxChecked() {
		Bundle b = getIntent().getExtras();
		boolean value = b.getBoolean("key");
		if (value) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	private class DownloadFile extends AsyncTask<String, Void, Void> {

		String fileName = "";
		ProgressDialog progressDialog;

		public DownloadFile(String string) {
			super();
			fileName = string;
		}

		@Override
		protected Void doInBackground(String... strings) {

			if (!alreadyExist && isNetworkAvailable()) {
				String fileUrl = strings[0]; // ->
				// http://maven.apache.org/maven-1.x/maven.pdf
				String fileName = strings[1]; // -> maven.pdf
				String extStorageDirectory = Environment
						.getExternalStorageDirectory().toString();
				File folder = new File(extStorageDirectory, "osym/yds-kpds");
				folder.mkdirs();

				File pdfFile = new File(folder, fileName);

				try {
					pdfFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileDownloader.downloadFile(fileUrl, pdfFile);

			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			String extStorageDirectory = Environment
					.getExternalStorageDirectory().toString();
			File folder = new File(extStorageDirectory, "osym/yds-kpds");
			folder.mkdir();

			File pdfFile = new File(folder, fileName);

			if (pdfFile.exists()) {
				if (isCheckboxChecked()) {
					view(findViewById(R.id.button1), fileName);
				} else {
					Toast.makeText(
							getApplicationContext(),
							getResources().getString(
									R.string.already_downloaded),
							Toast.LENGTH_LONG).show();

				}
				alreadyExist = true;
			} else {
				if (isNetworkAvailable()) {
					Toast.makeText(
							getApplicationContext(),
							getResources()
									.getString(R.string.starting_download),
							Toast.LENGTH_LONG).show();
					progressDialog = ProgressDialog.show(YDSActivity.this,
							getResources().getString(R.string.downloading),
							getResources().getString(R.string.please_wait),
							true);

				} else {
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.no_connection),
							Toast.LENGTH_LONG).show();
				}
				alreadyExist = false;
			}
		}

		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (isNetworkAvailable() && !alreadyExist) {
				progressDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.download_finished),
						Toast.LENGTH_LONG).show();

			}
			if (isCheckboxChecked() && !alreadyExist && isNetworkAvailable()) {
				view(findViewById(R.id.button1), fileName);
			}
		}

	}

	public void view(View v, String name) {
		File pdfFile = new File(Environment.getExternalStorageDirectory()
				+ "/osym/yds-kpds/" + name); // -> filename = maven.pdf
		Uri path = Uri.fromFile(pdfFile);
		Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
		pdfIntent.setDataAndType(path, "application/pdf");
		pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		try {
			startActivity(pdfIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(YDSActivity.this,
					getResources().getString(R.string.error_file_not_found),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yd, menu);
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
}
