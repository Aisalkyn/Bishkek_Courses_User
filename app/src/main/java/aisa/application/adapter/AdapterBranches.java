package aisa.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Branches;

public class AdapterBranches extends ArrayAdapter<Branches> {
    public AdapterBranches(Context context, List<Branches> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Branches model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);

        textView.setText(model.getAddress());

        return convertView;
    }

}

