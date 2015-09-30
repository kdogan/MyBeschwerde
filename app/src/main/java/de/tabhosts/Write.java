package de.tabhosts;

import de.category.CategoryWorker;
import de.category.Company;
import de.category.CompanyAdapter;
import de.mybeschwerde.R;
import de.user.shared.SessionManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

public class Write extends Activity {

    private final static Logger logger = Logger.getLogger(Write.class.getName());
    SessionManager session;
    private EditText msgBody;
    private EditText msgTitle;
    private TextView dateOfComplain;
    private Button senden;
    private Button abbrechen;
    // PopupWindows
    private PopupWindow popupWindow;
    private Button btnClosePopup;
    private Context context =this;
    // search variable
    private ArrayList<Company> companies;
    ListView list;
    CompanyAdapter adapter;
    EditText editsearch;

    CategoryWorker categoryWorker = new CategoryWorker();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);

        session = (SessionManager) getApplicationContext();
        msgTitle = (EditText) findViewById(R.id.title_editable);
        msgTitle.setTextColor(Color.BLUE);
        msgBody = (EditText) findViewById(R.id.body);
        dateOfComplain = (TextView) findViewById(R.id.complaine_date);
        // initialize buttons Senden and Abbrechen
        senden = (Button) findViewById(R.id.se);
        abbrechen = (Button) findViewById(R.id.abb);

        // here set current date for complains
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        SimpleDateFormat formatter = new SimpleDateFormat("d'/'M'/'y");
        String curDate = formatter.format(curDateTime);
        dateOfComplain.setText("" + curDate);
        initStep();
    }

    private void initStep() {
        logger.info("INFO : Methode initStep");
        senden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (msgTitle.getText().length() == 0) {
                   // msgTitle.setBackgroundResource(android.R.color.holo_red_light);
                    //initiatePopupWindow("Bitte geben Sie ein gültige Titel für Ihre Beschwerde ein!");
                    initNewDialog("Bitte geben Sie ein gültige Titel für Ihre Beschwerde ein!");
                    initStep();
                }else if(msgBody.getText().length() == 0){
                    initNewDialog("Bitte geben Sie Ihre Beschwerde ein!");
                    initStep();
                }else{
                    listAllCompanies();
                }
            }
        });

    }

    private void listAllCompanies() {
        setContentView(R.layout.search_company);
        companies = categoryWorker.getAllCompanies();
        // listView of companies
        list = (ListView) findViewById(R.id.listview);
        // Pass results to ListViewAdapter Class
        adapter = new CompanyAdapter(this,R.layout.search_company, companies);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Locate the EditText in search_company.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
// TODO Auto-generated method stub
            }
        });

    }

    private void initNewDialog(String meldung){
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom);

        SpannableString title=  new SpannableString(meldung);
        title.setSpan(new RelativeSizeSpan(0.9f), 0, title.length(), 0);
        title.setSpan(new ForegroundColorSpan(Color.RED), 0, title.length(), 0);
        dialog.setTitle(title);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
