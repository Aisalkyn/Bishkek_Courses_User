package aisa.application.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Contacts;

public class AdapterContacts extends ArrayAdapter<Contacts>
{
    public AdapterContacts(Context context, List<Contacts> list1) {
        super(context, 0, list1);
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        Contacts model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(model.getData());

        return convertView;
    }

}