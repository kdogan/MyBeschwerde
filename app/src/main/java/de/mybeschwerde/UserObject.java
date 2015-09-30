package de.mybeschwerde;

public class UserObject {

	private String user_id;
	private String user_name;
	private String user_mail;
	
	public UserObject(String user_id, String user_name, String user_mail) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_mail = user_mail;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

}
