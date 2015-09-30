package de.features;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import de.category.Categorie;
import de.category.CategoryIF;
import de.category.Company;
import de.category.Complains;
import de.category.Subject;
import de.mybeschwerde.MainActivity;
import android.util.Log;

public class ConnectJDBC {
	private final static Logger logger = Logger.getLogger(ConnectJDBC.class.getName());

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://54.72.2.32:3306/usr_web628_1";

	// Database credentials
	static final String USER = "kiradogan";
	static final String PASS = "6621288";
	Connection conn = null;
	ArrayList<CategoryIF> categoryList;
	ArrayList<Subject> subjectList;
    ArrayList<Company> companyList;
    ArrayList<Complains> complainList;
	Statement stmt;

    public ConnectJDBC() {
		categoryList = new ArrayList<CategoryIF>();
        companyList = new ArrayList<Company>();
		stmt = null;
		connectToDB();
	}

    public User fetchUserFromDB (Long userId) {
        User user = new User();
        try {
            logger.info("INFO : DB --> Fetch User form DB");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE id ="+userId;
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            // Retrieve by column name
            while (rs.next()) {
                Long user_id = rs.getLong("id");
                String user_name = rs.getString("name");
                String surname = rs.getString("surname");
                user.setId(user_id);
                user.setName(user_name);
                user.setSurname(surname);
            }
            // STEP 6: Clean-up environment
            rs.close();
            stmt.close();

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
        }
        return user;
    }

	public void fetchCategoryFromDB() {
		try {
			logger.info("INFO : DB --> Fetch Categories form DB");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM categories";
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CategoryIF category = new Categorie();
				// Retrieve by column name
				Long id = rs.getLong("id");
				String text_de = rs.getString("text_de");
				String text_en = rs.getString("text_en");
				String text_tr = rs.getString("text_tr");

				category.setId(id);
				category.setText_de(text_de);
				category.setText_en(text_en);
				category.setText_tr(text_tr);
				categoryList.add(category);
			}
            logger.info("INFO : "+categoryList.size()+" Categories are fetched");
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
//			conn.close();

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
//			try {
//				//if (conn != null)
//				//	conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}// end finally try
		}// end try
	}

    public void closeDB() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//	public void fetchSubjectFromDB(Long id) {
//
//		try {
//			logger.info("INFO : Creating statement...");
//			stmt = conn.createStatement();
//			String sql = "SELECT * FROM subjects WHERE category_id = " + id;
//			ResultSet rs;
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				CategoryIF subject = new Subject();
//				// Retrieve by column name
//				subject.setId(rs.getLong("id"));
//				subject.setText_de(rs.getString("text_de"));
//				subject.setText_en(rs.getString("text_en"));
//				subject.setText_tr(rs.getString("text_tr"));
//				subjectList.add(subject);
//			}
//			// STEP 6: Clean-up environment
//			rs.close();
//
//			stmt.close();
//			conn.close();
//		} catch (SQLException se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} catch (Exception e) {
//			// Handle errors for Class.forName
//			e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			}// nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}// end finally try
//		}// end try
//
//	}

	private void connectToDB() {
		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null)
				logger.info("INFO : connection success");

		} catch (Exception e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return;
		}
	}

	public ArrayList<CategoryIF> getCategoryList() {
		return categoryList;
	}
	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}

    public ArrayList<Company> getCompanies() {
        return companyList;
    }

    public void fetchCompaniesFromDB(Long id) {

        try {
            logger.info("INFO : Creating statement to fetch companies from DB");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM companies WHERE company_category = " + id;
            if(id.equals(000L)){
                sql = "SELECT * FROM companies";
            }
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int xy = 0;
            while (rs.next()) {
                Company company = new Company();
                // Retrieve by column name

                company.setId(rs.getLong("id"));
                company.setLogo(rs.getString("logo"));
                company.setTitle(rs.getString("title"));
                company.setName(rs.getString("name"));
                company.setEnable_notification(rs.getLong("enable_notification"));
                company.setNotification(rs.getString("notification"));
                company.setIsregistered(rs.getString("isregistered"));
                company.setRegistered_date(rs.getString("registered_date"));
                company.setEmail(rs.getString("e_mail"));
//                company.setState(rs.getString("state"));
//                company.setCity(rs.getString("city"));
//                company.setAddress(rs.getString("address"));
//                company.setLand_phone(rs.getString("land_phone"));
//                company.setMobile_phone(rs.getString("mobile_phone"));
//                company.setZip_code(rs.getString("zip_code"));
//                company.setWeb_site_url(rs.getString("web_site_url"));
//                company.setCompany_category(rs.getLong("company_category"));
                company.setIs_trash(rs.getString("is_trash"));
                companyList.add(company);
                xy++;
            }
            logger.info(xy + "Eintraege als company has ben fetched from DB");
            // STEP 6: Clean-up environment
            rs.close();

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


    public void fetchSubjectFromDB() {
        ArrayList<Subject> subs = new ArrayList<Subject>();
        try {
            logger.info("INFO : DB --> fetch all subjects form DB");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM subjects";
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Subject subject = new Subject();
                // Retrieve by column name
                subject.setId(rs.getLong("id"));
                subject.setText_de(rs.getString("text_de"));
                subject.setText_en(rs.getString("text_en"));
                subject.setText_tr(rs.getString("text_tr"));
                subject.setCategory_id(rs.getLong("category_id"));
                subs.add(subject);
            }
            subjectList = subs;
            logger.info("INFO : "+subs.size()+" Subjects are fetched");
            // STEP 6: Clean-up environment
            rs.close();

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

    public void fetchComplainsFromDB(Long company_id) {
        ArrayList<Complains> complainses = new ArrayList<Complains>();
        try {
            logger.info("INFO : DB --> fetch all complains for the company_id "+company_id+" form DB");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM complains WHERE company_id = "+company_id;
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Complains complain = new Complains();
                // Retrieve by column name
                complain.setId(rs.getLong("id"));
                complain.setCompany_id(rs.getLong("company_id"));
                complain.setCompanyLogo(rs.getString("logo"));
                complain.setComplain_category(rs.getLong("complain_category"));
                complain.setHeader(rs.getString("header"));
                complain.setIs_read(rs.getString("is_read"));
                complain.setText(rs.getString("text"));
                complain.setDate(rs.getString("date"));
                complain.setUserId(rs.getLong("user_id"));
                complainses.add(complain);
            }
            complainList = complainses;
            logger.info("INFO : "+complainses.size()+" Complains are fetched");
            // STEP 6: Clean-up environment
            rs.close();

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

    public ArrayList<Complains> getComplainList() {
        return complainList;
    }
}
