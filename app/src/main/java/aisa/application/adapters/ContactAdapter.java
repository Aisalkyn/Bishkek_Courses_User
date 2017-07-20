package aisa.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.models.Contacts;
import aisa.application.R;

/**
 * Created by admin on 7/17/17.
 */

public class ContactAdapter extends ArrayAdapter<Contacts> {



    public ContactAdapter (Context context, List<Contacts> list)
    {
        super(context, 0, list);
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        Contacts m = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_contact, parent, false);


            TextView textView1 = (TextView) convertView.findViewById(R.id.contactType);
            TextView textView12 = (TextView) convertView.findViewById(R.id.contactLink);



            textView1.setText(String.valueOf(m.getContacttype()));
            textView12.setText(m.getData());

        }

        return convertView;
    }

}

