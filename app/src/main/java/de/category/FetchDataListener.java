package de.category;

import java.util.ArrayList;
import java.util.List;

public interface FetchDataListener {
	 public void onFetchComplete(ArrayList<CategoryIF> data,int listingLayout);
	 public void onFetchFailure(String msg);
}
