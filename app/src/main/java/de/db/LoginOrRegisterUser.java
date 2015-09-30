package de.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import de.features.ConnectJDBC;
import de.features.DbUser;

public class LoginOrRegisterUser {

	private final static Logger logger = Logger.getLogger(ConnectJDBC.class.getName());
	
	private boolean INSERTED = false;
	private boolean LOGGED = false;
	private DbUser user;


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://54.72.2.32:3306/usr_web628_1";

	// Database credentials
	static final String USER = "kiradogan";
	static final String PASS = "6621288";
	Connection conn = null;
	Statement stmt = null;
	String msg = null;

	public LoginOrRegisterUser() {
		connectToDB();
	}
	private void connectToDB() {
		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (Exception e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return;
		}
	}

	public void registerUser(String firstName, String lastName, String email,String password) {

		try {
			// STEP 4: Execute a query
			logger.info("INFO : Creating statement...");
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			if (!isUserExisted(email)) {
				sb.append("INSERT INTO users VALUES (null,'");
				sb.append(firstName + "','"+ lastName);// firstname and lastname
				sb.append("',null"); // 4
				sb.append(",null"); // 5
				sb.append(",null"); // 6
				sb.append(",null"); // 7
				sb.append(",null"); // 8
				sb.append(",null"); // 9
				sb.append(",null"); // 10
				sb.append(",null"); // 11
				sb.append(",null"); // 12
				sb.append(",null"); // 13
				sb.append(",null"); // 14
				sb.append(",'"+password); // 15
				sb.append("',null"); // 16
				sb.append(",null"); // 17
				sb.append(",null"); // 18
				sb.append(",null"); // 19
				sb.append(",null"); // 20
				sb.append(",null"); // 21
				sb.append(",null"); // 22
				sb.append(",'"+email); // 23
				sb.append("',null"); // 24
				sb.append(",null"); // 25
				sb.append(",null"); // 26
				sb.append(",null"); // 27
				sb.append(",now()"); // 28 Date
				sb.append(",null"); // 29
				sb.append(",'user'"); // 30
				sb.append(",'yes'"); // 31
				sb.append(",null"); // 32
				sb.append(",null"); // 33
				sb.append(",1234567890987765"); // 34
				sb.append(",'no'"); // 35
				sb.append(",'no'"); //36
				sb.append(",'VERIFIED'"); // 37
				sb.append(",0)"); // 38
				
				stmt.executeUpdate(sb.toString());
				user = new DbUser();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setId(getIdUserIdFor(email));
				
				// check whether the user is inserted
				if (!isUserExisted(email)) {
					msg = "Diese Benutzer kann nicht hinzugefuegt werden!";
					logger.info("INFO : Fehlermelgung : " + msg);
				}else {
					INSERTED  = true;
				}
			} else {
				msg = "Angegebene Benutzer ist bereits vorhanden!";
				logger.info("INFO : Fehlermelgung : " + msg);
			}
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

	private Long getIdUserIdFor(String email) {
		ResultSet rs;
		String query = "SELECT id FROM users WHERE e_mail = '"+email+"'";
		try {
			rs = stmt.executeQuery(query);
			Long id = 1L;
			while(rs.next()){
				id = (long) rs.getInt("id");
			}
			
			return id;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void loginUser(String email, String password) {
		try {
			stmt = conn.createStatement();
			if (getUserByEmailAndPassword(email, password)) {
				user = new DbUser();
				String query = "SELECT * FROM users WHERE e_mail = '" + email
						+ "'";
				ResultSet rs;
				try {
					rs = stmt.executeQuery(query);
					// STEP 5: Extract data from result set
					while (rs.next()) {
						user.setId((long) rs.getInt("id"));
						user.setFirstName(rs.getString("name"));
						user.setLastName(rs.getString("surname"));
						user.setEmail(rs.getString("e_mail"));
					}
					logger.info("INFO : "+user.getFirstName()+" "+user.getLastName()+" with "+user.getEmail()+" is fetched from db");
					LOGGED = true;
					// STEP 6: Clean-up environment
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				msg = " Mail oder Password ist falsch!";
			}

			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	/**
	 * check user by given email and password
	 * @param email
	 * @param password
	 * @return true if mail and password is correct else false
	 */
	private boolean getUserByEmailAndPassword(String email, String password) {
		String query = "SELECT * FROM users WHERE e_mail = '" + email +"' AND password = '" + password+"'";
		return executeThisQueryAndGetTrueOrFalse(query);
	}

	/**
	 * check existent of user by given mail
	 * @param mail
	 * @return true if an user exist with given mail else false
	 */
	private boolean isUserExisted(String mail) {
		String query = "SELECT * FROM users WHERE e_mail = '" + mail+"'";
		return executeThisQueryAndGetTrueOrFalse(query);
	}
	

	/**
	 * execute given query on database
	 * @param query
	 * @return true or false
	 */
	private boolean executeThisQueryAndGetTrueOrFalse(String query) {
		logger.info("INFO : Query to Ceck : "+query);
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			
			if(!rs.next()){
				return false;
			}
			else {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getMessage() {
		return msg;
	}

	public boolean isInserted() {
		return INSERTED;
	}
	
	public boolean isLogged() {
		return LOGGED;
	}
	
	public DbUser getUser(){
		return user;
	}

}
