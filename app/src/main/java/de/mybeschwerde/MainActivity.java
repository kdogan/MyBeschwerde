package de.mybeschwerde;

import java.util.logging.Logger;

import de.tabhosts.Home;
import de.tabhosts.NewRead;
import de.tabhosts.Read;
import de.tabhosts.Login;
import de.tabhosts.Write;

import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;
import android.os.StrictMode;

public class MainActivity extends TabActivity {

	private final static Logger logger = Logger.getLogger(MainActivity.class.getName());
	private static Boolean isRegistred = true;
	static TabHost tabHost;
	static TabHost.TabSpec spec;
	static Intent intent;
    static Resources res;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
		res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		// this.spec; // Reusable TabSpec for each tab
		// this.intent; // Reusable Intent for each tab
		setAllTabs(0);

	}

	/**
	 * By calling of this method set all tabs again
	 *
	 * @param i
	 *            this parameter is given for current content
	 */
	private void setAllTabs(int i) {
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, Home.class);
		spec = tabHost.newTabSpec("home").setIndicator("", res.getDrawable(R.drawable.ic_tab_home)).setContent(intent);
		tabHost.addTab(spec);
		logger.info("Tabhost Home has been created");

		// Do the same for the other tabs

		intent = new Intent().setClass(this, Write.class);
		spec = tabHost.newTabSpec("write").setIndicator("", res.getDrawable(R.drawable.ic_tab_write)).setContent(intent);
		tabHost.addTab(spec);
		logger.info("Tabhost Write has been created");

		//intent = new Intent().setClass(this, Read.class);
        intent = new Intent().setClass(this, Read.class);
		spec = tabHost.newTabSpec("read").setIndicator("", res.getDrawable(R.drawable.ic_tab_read)).setContent(intent);
		tabHost.addTab(spec);
		logger.info("Tabhost Read has been created");

		intent = new Intent().setClass(this, Login.class);
		spec = tabHost.newTabSpec("me").setIndicator("", res.getDrawable(R.drawable.ic_tab_me)).setContent(intent);
		tabHost.addTab(spec);
		logger.info("Tabhost Me has been created");

		// set tab which one you want open first time 0 or 1 or 2
		setActualContent(i);

	}

	public static Resources getRes() {
		return res;
	}

	public static void setRes(Resources res) {
		MainActivity.res = res;
	}

	public static TabHost getMyTabHost() {
		return tabHost;
	}

	public static void setMyTabHost(TabHost tabHost) {
		MainActivity.tabHost = tabHost;
	}

	public static TabHost.TabSpec getSpec() {
		return spec;
	}

	public static void setSpec(TabHost.TabSpec spec) {
		MainActivity.spec = spec;
	}

	public static Intent getMyIntent() {
		return intent;
	}

	public static void setMyIntent(Intent intent) {
		MainActivity.intent = intent;
	}

	/**
	 * calling of this method determines current content home(i=0), write(i=1)
	 * read(i=2) login(i=3)
	 *
	 * @param i
	 */
	public static void setActualContent(int i) {
		if (i > -1 && i < 4) {
			tabHost.setCurrentTab(i);
		} else {
			throw new IllegalArgumentException("out of aceptable numbers!!!");
		}
	}

	public Boolean isRegistred() {
		return isRegistred;
	}

	public static void setIsRegistred(Boolean value) {
		isRegistred = value;
	}

}
