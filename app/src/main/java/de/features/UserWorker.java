package de.features;


public class UserWorker {
	
	ConnectJDBC db_connect;
    private User user;
	
	public UserWorker(){
		db_connect = new ConnectJDBC();
	}

	public void insertUser(String id,String name, String email,String passwd) {
		//TODO : something
		String query_insert = "INSERT INTO users(unique_id, name, email, encrypted_password, salt, created_at, created_by) "
				+ "VALUES('"+id+", "+name+", "+email+", "+encrytedPasswd(passwd)+", '$salt', NOW()), "+id+")" ;
		
	}
    public void fetchUser(Long userId){
        this.user = db_connect.fetchUserFromDB(userId);
    }
    public User getUser(){
        return user;
    }

    /**
	 * with this method must been generated password 
	 * @param passwd
	 * @return
	 */
	private String encrytedPasswd(String passwd) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser() {
		//TODO : something
	}
	public void updateUser() {
		//TODO : something
	}
}
