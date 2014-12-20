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
import android.widget.TextView;
import android.widget.Toast;

public class OsysActivity extends Activity {

	static int layoutId = 1;
	static int yearId = 0;
	static boolean alreadyExist = false;
	static String[][] links = {
			{
					"http://www.osym.gov.tr/dosya/1-69252/h/lys1matematiktesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69251/h/lys1geometritesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69254/h/lys2fiziktesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69255/h/lys2kimyatesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69253/h/lys2biyolojitesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69257/h/lys3tdetesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69256/h/lys3cog-1testi.pdf",
					"http://www.osym.gov.tr/dosya/1-69260/h/lys4tarihtesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69258/h/lys4cografya-2testi.pdf",
					"http://www.osym.gov.tr/dosya/1-69259/h/lys4felsefe-dkabtesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69261/h/lys5almancatesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69262/h/lys5fransizcatesti.pdf",
					"http://www.osym.gov.tr/dosya/1-69263/h/lys5ingilizcetesti.pdf" },
			{ "http://www.osym.gov.tr/dosya/1-60231/h/lys1matematik.pdf",
					"http://www.osym.gov.tr/dosya/1-60230/h/lys1geometri.pdf",
					"http://www.osym.gov.tr/dosya/1-60233/h/lys2fizik.pdf",
					"http://www.osym.gov.tr/dosya/1-60234/h/lys2kimya.pdf",
					"http://www.osym.gov.tr/dosya/1-60232/h/lys2biyoloji.pdf",
					"http://www.osym.gov.tr/dosya/1-60236/h/lys3turkdili.pdf",
					"http://www.osym.gov.tr/dosya/1-60235/h/lys3cografya.pdf",
					"http://www.osym.gov.tr/dosya/1-60239/h/lys4tarih.pdf",
					"http://www.osym.gov.tr/dosya/1-60237/h/lys4cografya2.pdf",
					"http://www.osym.gov.tr/dosya/1-60238/h/lys4felsefe.pdf",
					"http://www.osym.gov.tr/dosya/1-60240/h/lys5almanca.pdf",
					"http://www.osym.gov.tr/dosya/1-60241/h/lys5fransizca.pdf",
					"http://www.osym.gov.tr/dosya/1-60242/h/lys5ingilizce.pdf" },
			{
					"http://www.osym.gov.tr/dosya/1-57805/h/lys1matematik.pdf",
					"http://www.osym.gov.tr/dosya/1-57804/h/lys1geometri.pdf",
					"http://www.osym.gov.tr/dosya/1-57851/h/lys2fizik.pdf",
					"http://www.osym.gov.tr/dosya/1-57852/h/lys2kimya.pdf",
					"http://www.osym.gov.tr/dosya/1-57850/h/lys2biyoloji.pdf",
					"http://www.osym.gov.tr/dosya/1-57854/h/lys3edebiyat.pdf",
					"http://www.osym.gov.tr/dosya/1-57853/h/lys3cografya1.pdf",
					"http://www.osym.gov.tr/dosya/1-57808/h/lys4tarih.pdf",
					"http://www.osym.gov.tr/dosya/1-57806/h/lys4cografya2.pdf",
					"http://www.osym.gov.tr/dosya/1-57807/h/lys4felsefegrubu.pdf",
					"http://www.osym.gov.tr/dosya/1-57809/h/lys5yabancidil.pdf",
					"http://www.osym.gov.tr/dosya/1-57809/h/lys5yabancidil.pdf",
					"http://www.osym.gov.tr/dosya/1-57809/h/lys5yabancidil.pdf" },
			{ "http://osym.gov.tr/dosya/1-60263/h/2010lys1mat.pdf",
					"http://osym.gov.tr/dosya/1-60262/h/2010lys1geo.pdf",
					"http://osym.gov.tr/dosya/1-60265/h/2010lys2fiz.pdf",
					"http://osym.gov.tr/dosya/1-60266/h/2010lys2kim.pdf",
					"http://osym.gov.tr/dosya/1-60264/h/2010lys2biy.pdf",
					"http://osym.gov.tr/dosya/1-60268/h/2010lys3tur.pdf",
					"http://osym.gov.tr/dosya/1-60267/h/2010lys3cog.pdf",
					"http://osym.gov.tr/dosya/1-60271/h/2010lys4tar.pdf",
					"http://osym.gov.tr/dosya/1-60269/h/2010lys4cog.pdf",
					"http://osym.gov.tr/dosya/1-60270/h/2010lys4fel.pdf",
					"http://osym.gov.tr/dosya/1-60272/h/2010lys5alm.pdf",
					"http://osym.gov.tr/dosya/1-60275/h/2010lys5fra.pdf",
					"http://osym.gov.tr/dosya/1-60278/h/2010lys5ing.pdf" },
			{
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_tur.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_sos1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_mat1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_fen1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_edsos.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_sos2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_mat2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_fen2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2009/2009OSS/2009oss_cevap_anahtari.pdf" },
			{
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_tur.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_sos1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_mat1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_fen1.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_edsos.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_sos2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_mat2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_fen2.pdf",
					"http://dokuman.osym.gov.tr/pdfdokuman/arsiv/2008/2008OSS/oss_cevap_anahtari.pdf" },
			{
					"http://osym.gov.tr/dosya/1-60296/h/2010ygsturkcetesti.pdf",
					"http://osym.gov.tr/dosya/1-60295/h/2010ygssosbiltesti.pdf",
					"http://osym.gov.tr/dosya/1-60293/h/2010ygsmattesti.pdf",
					"http://osym.gov.tr/dosya/1-60291/h/2010ygsfenbiltesti.pdf",
					"http://osym.gov.tr/dosya/1-60290/h/2010ygscevan.pdf" } };
	static String[][] names = {
			{ "2013-matematik.pdf", "2013-geometri.pdf", "2013-fizik.pdf",
					"2013-kimya.pdf", "2013-biyoloji.pdf",
					"2013-turk_dili_ve_edebiyati.pdf", "2013-cografya1.pdf",
					"2013-tarih.pdf", "2013-cografya2.pdf", "2013-felsefe.pdf",
					"2013-almanca.pdf", "2013-fransizca.pdf",
					"2013-ingilizce.pdf" },
			{ "2012-matematik.pdf", "2012-geometri.pdf", "2012-fizik.pdf",
					"2012-kimya.pdf", "2012-biyoloji.pdf",
					"2012-turk_dili_ve_edebiyati.pdf", "2012-cografya1.pdf",
					"2012-tarih.pdf", "2012-cografya2.pdf", "2012-felsefe.pdf",
					"2012-almanca.pdf", "2012-fransizca.pdf",
					"2012-ingilizce.pdf" },
			{ "2011-matematik.pdf", "2011-geometri.pdf", "2011-fizik.pdf",
					"2011-kimya.pdf", "2011-biyoloji.pdf",
					"2011-turk_dili_ve_edebiyati.pdf", "2011-cografya1.pdf",
					"2011-tarih.pdf", "2011-cografya2.pdf", "2011-felsefe.pdf",
					"2011-almanca.pdf", "2011-fransizca.pdf",
					"2011-ingilizce.pdf" },
			{ "2010-matematik.pdf", "2010-geometri.pdf", "2010-fizik.pdf",
					"2010-kimya.pdf", "2010-biyoloji.pdf",
					"2010-turk_dili_ve_edebiyati.pdf", "2010-cografya1.pdf",
					"2010-tarih.pdf", "2010-cografya2.pdf", "2010-felsefe.pdf",
					"2010-almanca.pdf", "2010-fransizca.pdf",
					"2010-ingilizce.pdf" },
			{ "2009-turkce.pdf", "2009-sosyal_bilimler_1.pdf",
					"2009-matematik_1.pdf", "2009-fen_bilimleri_1.pdf",
					"2009-edebiyat_sosyal_bilimler.pdf",
					"2009-sosyal_bilimler_2.pdf", "2009-matematik_2.pdf",
					"2009-fen_bilimleri_2.pdf", "2009-cevap_anahtari.pdf" },
			{ "2008-turkce.pdf", "2008-sosyal_bilimler_1.pdf",
					"2008-matematik_1.pdf", "2008-fen_bilimleri_1.pdf",
					"2008-edebiyat_sosyal_bilimler.pdf",
					"2008-sosyal_bilimler_2.pdf", "2008-matematik_2.pdf",
					"2008-fen_bilimleri_2.pdf", "2008-cevap_anahtari.pdf" },
			{ "2010-ygs-turkce.pdf", "2010-ygs-sosyal_bilimler.pdf",
					"2010-ygs-matematik.pdf", "2010-ygs-fen_bilimleri.pdf",
					"2010-ygs-cevap_anahtari.pdf" } };

	public void onBackPressed() {
		if (layoutId == 2) {
			onCreate(new Bundle());
			layoutId = 1;
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_osys);
		getActionBar().hide();

		final Button osysButton1 = (Button) findViewById(R.id.button1);
		final Button osysButton2 = (Button) findViewById(R.id.button2);
		final Button osysButton3 = (Button) findViewById(R.id.button3);
		final Button osysButton4 = (Button) findViewById(R.id.button4);
		final Button osysButton5 = (Button) findViewById(R.id.button5);
		final Button osysButton6 = (Button) findViewById(R.id.button6);
		final Button osysButton7 = (Button) findViewById(R.id.button7);
		final Button osysButton8 = (Button) findViewById(R.id.button8);
		final Button osysButton9 = (Button) findViewById(R.id.button9);
		final Button osysButton10 = (Button) findViewById(R.id.button10);

		osysButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("ygs2013.pdf")
						.execute(
								"http://dokuman.osym.gov.tr/pdfdokuman/2013/OSYS/24.03.2013%20YGS.pdf",
								"ygs2013.pdf");
			}
		});
		osysButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys2);
				layoutId = 2;
				yearId = 0;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2013 LYS Sorularý");
			}
		});

		osysButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("ygs2012.pdf")
						.execute(
								"http://dokuman.osym.gov.tr/pdfdokuman/2012/OSYS/2012YGSSorular.pdf",
								"ygs2012.pdf");
			}
		});

		osysButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys2);
				layoutId = 2;
				yearId = 1;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2012 LYS Sorularý");
			}
		});

		osysButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new DownloadFile("ygs2011.pdf")
						.execute(
								"http://www.osym.gov.tr/dosya/1-60611/h/2011ygssorulari.pdf",
								"ygs2011.pdf");
			}
		});

		osysButton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys2);
				layoutId = 2;
				yearId = 2;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2011 LYS Sorularý");
			}
		});

		osysButton7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys2010);
				layoutId = 2;
				yearId = 6;

			}
		});

		osysButton8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys2);
				layoutId = 2;
				yearId = 3;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2010 LYS Sorularý");
			}
		});

		osysButton9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys3);
				layoutId = 2;
				yearId = 4;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2009 ÖSS Sorularý");
			}
		});

		osysButton10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setContentView(R.layout.activity_osys3);
				layoutId = 2;
				yearId = 5;
				final TextView text1 = (TextView) findViewById(R.id.textView1);
				text1.setText("2008 ÖSS Sorularý");
			}
		});

	}

	public void startDownload(View view) {
		String tag = (String) view.getTag();
		int tagId = Integer.parseInt(tag);
		new DownloadFile(names[yearId][tagId - 1]).execute(
				links[yearId][tagId - 1], names[yearId][tagId - 1]);
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
				File folder = new File(extStorageDirectory, "osym/osys");
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
			File folder = new File(extStorageDirectory, "osym/osys");
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
					progressDialog = ProgressDialog.show(OsysActivity.this,
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
				+ "/osym/osys/" + name); // -> filename = maven.pdf
		Uri path = Uri.fromFile(pdfFile);
		Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
		pdfIntent.setDataAndType(path, "application/pdf");
		pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		try {
			startActivity(pdfIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(OsysActivity.this,
					getResources().getString(R.string.error_file_not_found),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.osys, menu);
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
