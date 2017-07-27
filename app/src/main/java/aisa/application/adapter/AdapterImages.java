package aisa.application.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Images;

public class AdapterImages extends ArrayAdapter<Images> {
    public AdapterImages(Context context, List<Images> list) {
        super(context, 0, list);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        Images model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(model.getImagePath());

        return convertView;
    }
}