package aisa.application.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Services;

public class AdapterServices extends ArrayAdapter<Services> {
    public AdapterServices(Context context, List<Services> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Services model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);

        textView.setText(model.getName());

        return convertView;
    }

}