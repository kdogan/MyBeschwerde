package de.tabhosts;

import de.mybeschwerde.MainActivity;
import de.mybeschwerde.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;

public class Home extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		goToWriteOrRead();
	}
	
	/**
	 * this is for both big images of home screen
	 * if one of them is called then change the actual layout...
	 */
	public void goToWriteOrRead() {
		setContentView(R.layout.home);
		final ImageButton write_btn = (ImageButton) findViewById(R.id.write_at_home_button);
		final ImageButton read_btn = (ImageButton) findViewById(R.id.read_at_home_button);
		
		write_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.setActualContent(1);
			}
		});
		read_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.setActualContent(2);
			}
		});
 
	}
}