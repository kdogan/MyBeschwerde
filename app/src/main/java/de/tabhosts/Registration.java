package de.tabhosts;

import java.util.logging.Logger;

import de.mybeschwerde.MainActivity;
import de.mybeschwerde.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class Registration extends Activity {

	private final static Logger logger = Logger.getLogger(Registration.class.getName());
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Set View to register.xml
	        setContentView(R.layout.register);
	 
	        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
	 
	        // Listening to Login Screen link
	        loginScreen.setOnClickListener(new View.OnClickListener() {
	        	//TODO set here the isRegistred of MainActivity on false
			public void onClick(View arg0) {
//				finish();
			}
	        });
	    }
	
}
