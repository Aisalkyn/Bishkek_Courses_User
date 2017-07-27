package aisa.application.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.SubCategories;

public class AdapterSubCategory extends ArrayAdapter<SubCategories> {
    private static final int padding = 10;
    public AdapterSubCategory(Context context, List<SubCategories> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(getItem(position).getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setPadding(padding, padding, padding, padding);
        label.setTextSize(20.f);
        label.setScrollBarSize(30);
        label.setText(getItem(position).getName());

        return label;
    }
}
