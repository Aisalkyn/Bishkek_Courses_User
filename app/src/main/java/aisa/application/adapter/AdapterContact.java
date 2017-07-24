package aisa.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Contact;

/**
 * Created by Alier on 7/22/2017.
 */

public class AdapterContact extends ArrayAdapter<Contact> {

    public AdapterContact(Context context, List<Contact> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_contact, parent, false);
        }

        TextView type = (TextView) convertView.findViewById(R.id.contactType);
        TextView link = (TextView) convertView.findViewById(R.id.contactLink);

        type.setText(String.valueOf(contact.getContactType()));
        link.setText(contact.getData());

        return convertView;
    }
}
