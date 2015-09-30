package de.tabhosts;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import de.category.CategoryIF;
import de.category.CategoryWorker;
import de.category.FetchDataListener;
import de.category.Subject;
import de.mybeschwerde.R;

/**
 * Created by kdogan on 14.12.2014.
 */
public class NewRead extends ListActivity implements FetchDataListener {
    private final static Logger logger = Logger.getLogger(NewRead.class.getName());

    ListView listView = (ListView) findViewById(android.R.id.list);
    HashMap<Category,ArrayList<Subject>> catSubjMap  = new HashMap<Category, ArrayList<Subject>>();
    CategoryWorker categoryWorker = new CategoryWorker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
        initView();

    }

    private void initView() {
        Thread t = new Thread(){
            public void run(){
                logger.info("INFO : new Thread is started for init View ");
                onFetchComplete(categoryWorker.getCategories(), R.layout.read_category);
            }
        };
        t.start();
    }

    @Override
    public void onFetchComplete(ArrayList<CategoryIF> data, int listingLayout) {

    }

    @Override
    public void onFetchFailure(String msg) {

    }
}
