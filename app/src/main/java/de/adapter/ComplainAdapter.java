package de.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.category.Complains;
import de.features.UserWorker;
import de.mybeschwerde.R;

/**
 * Created by Kamuran Dogan on 21.12.2014.
 */
public class ComplainAdapter extends ArrayAdapter<Complains> {

    private List<Complains> items;
    private int resource = R.layout.complain_msg_list;

    public ComplainAdapter(Context context, int readCategoryXml, List<Complains> items) {
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

        Complains complains = items.get(position);
        UserWorker userWorker = new UserWorker();
        userWorker.fetchUser(complains.getUserId());
        String nameOfUser = userWorker.getUser().getName() + " " + userWorker.getUser().getSurname();


        if(complains != null) {
//	        ImageView icon = (ImageView)v.findViewById(R.id.appIcon);
            TextView complainUser = (TextView)v.findViewById(R.id.complain_user);
            TextView msgHeader = (TextView)v.findViewById(R.id.msgHeader);
            TextView date = (TextView)v.findViewById(R.id.dateOfComplain);
            TextView text = (TextView)v.findViewById(R.id.compMsgBody);
            TextView categoryCounter = (TextView)v.findViewById(R.id.categoryCaounter);

            //TODO here check which language then add correspond text text_de is default
            if(complainUser != null) {

                complainUser.setText(nameOfUser);
                msgHeader.setText(complains.getHeader());
                date.setText(complains.getDate());
                text.setText(complains.getText());

            }
        }

        return v;
    }
}
