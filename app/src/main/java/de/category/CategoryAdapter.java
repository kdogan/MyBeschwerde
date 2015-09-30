package de.category;

import java.util.List;

import org.junit.experimental.categories.Categories.CategoryFilter;

import de.mybeschwerde.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<CategoryIF> {
	
	 private List<CategoryIF> items;
	 private int resource = R.layout.read_category;
	
	 public CategoryAdapter(Context context, int readCategoryXml, List<CategoryIF> items) {
	        super(context, readCategoryXml, items);
	        this.items = items;
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
	         
	        CategoryIF category = items.get(position);
	         
	        if(category != null) {
//	            ImageView icon = (ImageView)v.findViewById(R.id.appIcon);
	            TextView CategoryName = (TextView)v.findViewById(R.id.categoryName);
	            TextView categoryCounter = (TextView)v.findViewById(R.id.categoryCaounter);
	             
	            //TODO here check which language then add correspond text text_de is default 
	            if(CategoryName != null) {
	            	CategoryName.setText(category.getText_de());
	            }
	        }
	         
	        return v;
	    }
}
