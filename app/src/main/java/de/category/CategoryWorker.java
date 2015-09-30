package de.category;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.features.ConnectJDBC;

public class CategoryWorker {
	
	private final static Logger logger = Logger.getLogger(CategoryWorker.class.getName());
    private ArrayList<Subject> subjects;
    private ArrayList<CategoryIF> categories;
	
	private ConnectJDBC db_conn;
    public CategoryWorker(Long user_id){
        db_conn = new ConnectJDBC();
    }
	public CategoryWorker() {
        // TODO: here can be started two Threads
        db_conn = new ConnectJDBC();//create connection
        this.categories = fetchCategory();
        this.subjects = fetchSubjectsFromDB();
        db_conn.closeDB();//close the connection
	}
	public ArrayList<CategoryIF> getCategories(){
        return this.categories;
    }

	private ArrayList<CategoryIF> fetchCategory() {
		db_conn.fetchCategoryFromDB();
		return db_conn.getCategoryList();
	}

    public ArrayList<Company> fetchCompanies(Long id) {
        db_conn = new ConnectJDBC();
        db_conn.fetchCompaniesFromDB(id);
        db_conn.closeDB();
        return db_conn.getCompanies();
    }
    public ArrayList<Subject> fetchSubjectsFromDB(){
        db_conn.fetchSubjectFromDB();
        return db_conn.getSubjectList();
    }

	public ArrayList<Subject> getSubjectsForThisCategoryId(long id) {
        ArrayList<Subject> subs = new ArrayList<Subject>();
//        for(CategoryIF cate : categories){
//            Long cate_id = cate.getId();
            for(Subject s : subjects){
                if(s.getCategory_id().equals(id)){
                    subs.add(s);
                }
            }
//        }
		return subs;
	}

    public ArrayList<Complains> fetchComplains(Long company_id) {
        db_conn = new ConnectJDBC();
        db_conn.fetchComplainsFromDB(company_id);
        db_conn.closeDB();
        return db_conn.getComplainList();
    }

    public ArrayList<Company> getAllCompanies() {
        db_conn = new ConnectJDBC();
        db_conn.fetchCompaniesFromDB(000L);
        db_conn.closeDB();
        return db_conn.getCompanies();
    }
}
