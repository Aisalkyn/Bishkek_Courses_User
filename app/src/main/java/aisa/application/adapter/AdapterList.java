package aisa.application.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;

public class AdapterList extends ArrayAdapter<String> {
    public AdapterList(Context context, List<String> listC) {
        super(context, 0, listC);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false);

            TextView textView = (TextView) convertView.findViewById(R.id.text);

            textView.setText(getItem(position));
        }

        return convertView;
    }

}
