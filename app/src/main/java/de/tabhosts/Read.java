package de.tabhosts;

import java.util.ArrayList;
import java.util.logging.Logger;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import de.adapter.ComplainAdapter;
import de.category.Categorie;
import de.category.CategoryAdapter;
import de.category.CategoryIF;
import de.category.CategoryWorker;
import de.category.Company;
import de.category.CompanyAdapter;
import de.category.Complains;
import de.category.FetchDataListener;
import de.category.Subject;
import de.category.SubjectAdapter;
import de.mybeschwerde.R;


public class Read extends ListActivity implements FetchDataListener {

    private final static Logger logger = Logger.getLogger(Read.class.getName());

//    HashMap<Category,ArrayList<Subject>> catSubjMap  = new HashMap<Category, ArrayList<Subject>>();
    private ProgressDialog dialog;

    Long current_category_id = null;
    CategoryWorker categoryWorker;
    Categorie category;
    Subject subject;
    Company company;
    ListView listView;
    View read_header;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);


        listView = (ListView) findViewById(android.R.id.list);
        // here added Backbutton on the list
        read_header = (View) getLayoutInflater().inflate(R.layout.read_header, null);
        listView.addHeaderView(read_header);
        categoryWorker = new CategoryWorker();

        initView();
    }

    /*
     * this is start method
     * here fetched all categories from DB
     */
    private void initView() {
        logger.info("INFO : new Thread is started for init View ");
        onFetchComplete(categoryWorker.getCategories(), R.layout.read_category);
        goToReadyForEvent();
    }

    @Override
    public void onFetchComplete(ArrayList<CategoryIF> data, int listingLayout) {
//    	dialog = ProgressDialog.show(this, "", "Loading...");
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, listingLayout, data);
        // set the adapter to list
        setListAdapter(categoryAdapter);
        // dismiss the progress dialog
//        if(dialog != null)  dialog.dismiss();

    }

    /*
     * hier wird alle Subjects gelistet
     */
    public void onFetchComplete3(ArrayList<Subject> data, int listingLayout) {
//    	dialog = ProgressDialog.show(this, "", "Loading...");
        SubjectAdapter subjectAdapter = new SubjectAdapter(this, listingLayout, data);
        // set the adapter to list
        setListAdapter(subjectAdapter);
        // dismiss the progress dialog
//        if(dialog != null)  dialog.dismiss();

    }
    /*
     * hier wirda alle Firmen gelistet
     */
    public void onFetchComplete2(ArrayList<Company> data, int listingLayout) {
        logger.info(data.size() + " companies are fetched from DB");
        CompanyAdapter companyAdapter = new CompanyAdapter(this, listingLayout, data);
        // set the adapter to list
        setListAdapter(companyAdapter);
        // dismiss the progress dialog
        if (dialog != null) dialog.dismiss();
    }

    /*
     * hier wird alle beschwerde gelistet
     */
    public void onFetchComplete4(ArrayList<Complains> data, int listingLayout) {
        logger.info("INFO : "+data.size() + " Complains are listed");
        ComplainAdapter complainAdapter = new ComplainAdapter(this, listingLayout, data);
        // set the adapter to list
        setListAdapter(complainAdapter);
        // dismiss the progress dialog
        if (dialog != null) dialog.dismiss();
    }


    @Override
    public void onFetchFailure(String msg) {
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void goToReadyForEvent() {
        logger.info("INFO : Methode goToReadForEvent");
        ImageButton back_Button = (ImageButton)findViewById(R.id.imageReadHeader);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = (Categorie) listView.getItemAtPosition(position);
                // ListView Clicked item value
                logger.info("INFO : Category type "+category.getText_de()+"has been selected");
                current_category_id = category.getId();
                onFetchComplete3(categoryWorker.getSubjectsForThisCategoryId(category.getId()), R.layout.read_category);
                goToReadyForEventSubjects();
            }

        });
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
            }
        });

    }
    /*
     * here ara listed all sub categories
     */
    private void goToReadyForEventSubjects(){
        logger.info("INFO : Methode goToReadForEventSubject");
        ImageButton back_Button = (ImageButton)findViewById(R.id.imageReadHeader);

        // ListView Iem Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                subject = (Subject) listView.getItemAtPosition(position);
                // ListView Clicked item value
                // Hier werden alle Firmen von aktuelle Kategorie geladen.
                logger.info("INFO : Subject type "+category.getText_de()+"has been selected");
                //onFetchComplete2(categoryWorker.fetchCompanies(subject.getCategory_id()), R.layout.complain_msg_list);
                onFetchComplete2(categoryWorker.fetchCompanies(subject.getCategory_id()), R.layout.read_category);
                goToReadyForEventCompanies();
            }

        });
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
            }
        });
    }

    /*
     * here are listed all companies
     */
    private void goToReadyForEventCompanies(){
        logger.info("INFO : Methode goToReadForEventCompanies");
        ImageButton back_Button = (ImageButton)findViewById(R.id.imageReadHeader);

        // ListView Iem Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                company = (Company) listView.getItemAtPosition(position);
                // ListView Clicked item value
                logger.info("INFO : Company with name "+company.getName()+"has been selected");
                //onFetchComplete2(categoryWorker.fetchCompanies(subject.getCategory_id()), R.layout.complain_msg_list);
                onFetchComplete4(categoryWorker.fetchComplains(company.getId()), R.layout.complain_msg_list);
            }

        });
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
                // TODO go to subjects
                // onFetchComplete3(categoryWorker.getSubjectsForThisCategoryId(category.getId()), R.layout.read_category);
            }
        });
    }

}
