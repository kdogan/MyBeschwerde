package de.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.mybeschwerde.R;
import de.tabhosts.Write;

/**
 * Created by kdogan on 13.12.2014.
 */
public class CompanyAdapter extends ArrayAdapter<Company> {

    private List<Company> items = null;
    private ArrayList<Company> arrayList;
    private int resource = R.layout.read_category;

    public CompanyAdapter(Context context, int readCategoryXml, List<Company> items) {
        super(context, readCategoryXml, items);
        this.items = items;
        this.arrayList = new ArrayList<Company>();
        this.arrayList.addAll(items);
        if(this.items.equals(null)){
            System.out.println("INFO : KEINE DATEN");
        }
        System.out.println("ITEMs Size : "+this.items.size());
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(resource, null);
        }

        Company company = items.get(position);

        if(company != null) {
//	            ImageView icon = (ImageView)v.findViewById(R.id.appIcon);
            TextView CategoryName = (TextView)v.findViewById(R.id.categoryName);
            TextView categoryCounter = (TextView)v.findViewById(R.id.categoryCaounter);

            //TODO here check which language then add correspond text text_de is default
            if(CategoryName != null) {
                CategoryName.setText(company.getName());
            }
        }

        return v;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(arrayList);
        }
        else
        {
            for (Company company : arrayList)
            {
                if (company.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    items.add(company);
                }
            }
        }
        notifyDataSetChanged();
    }
}
