package de.user.shared;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * @author Kamuran Dogan
 */
public class UserPreferences {
	
	private static final String USER_PREFS = "USER_PREFS";
	private SharedPreferences appSharedPrefs;
	private SharedPreferences.Editor prefsEditor;
	private String user_name = "user_name_prefs";
	private String user_id = "user_id_prefs";
	
	public UserPreferences (Context context) {
		this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
		prefsEditor = appSharedPrefs.edit();
	}
	
	public int getUserId() {
		return appSharedPrefs.getInt(user_id, 0);
	}
	public void setUserId(int _user_id) {
		prefsEditor.putInt(user_id, _user_id).commit();
	}
	public String getUserName() {
		return appSharedPrefs.getString(user_name,"unkown");
	}
	public void setUserName(String _user_name) {
		prefsEditor.putString(user_name,_user_name).commit();
	}
	
}
